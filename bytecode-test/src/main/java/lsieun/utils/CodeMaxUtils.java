package lsieun.utils;

import lsieun.bytecode.classfile.attrs.method.ExceptionTable;
import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.utils.AccessFlagUtils;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.facet.BranchInstruction;
import lsieun.bytecode.opcode.facet.IfInstruction;
import lsieun.bytecode.opcode.facet.LocalVariableInstruction;
import lsieun.bytecode.opcode.facet.SelectInstruction;
import lsieun.bytecode.opcode.utils.BranchStack;
import lsieun.bytecode.opcode.utils.BranchTarget;
import lsieun.bytecode.opcode.utils.InstructionChain;
import lsieun.bytecode.utils.AdvancedTypeUtils;

public class CodeMaxUtils {
    /**
     * Compute maximum number of local variables.
     */
    public static int computeMaxLocals(final int access_flags, final Type[] arg_types, final InstructionChain chain) {
        int max_locals = 0;
        if (chain != null) {
            int max = AccessFlagUtils.isStatic(access_flags) ? 0 : 1;
            if (arg_types != null) {
                for (final Type arg_type : arg_types) {
                    max += arg_type.getSize();
                }
            }

            Instruction current = chain.start;
            while (current != null) {
                if (current instanceof LocalVariableInstruction) {
                    LocalVariableInstruction item = (LocalVariableInstruction) current;
                    // 注意：index是local variable array的索引，这个索引是从0开始记数的
                    // 这个索引值（index）转换成“占用的slot数量”时，还需要加上1或2
                    int index = item.getIndex();

                    // Int/Float/Object类型的数据占用1个slot
                    // Long或Double类型的数据要占用2个slot
                    index += AdvancedTypeUtils.getType(item).getSize();

                    if (index > max) {
                        max = index;
                    }
                }
                current = current.next;
            }

            max_locals = max;
        } else {
            max_locals = 0;
        }
        return max_locals;
    }

    public static int computeMaxStack(final ConstantPool constant_pool, final InstructionChain chain, final ExceptionTable[] exception_array) {
        int max_stack = 0;

        final BranchStack branchTargets = new BranchStack();
        /* Initially, populate the branch f_stack with the exception
         * handlers, because these aren't (necessarily) branched to
         * explicitly. in each case, the f_stack will have depth 1,
         * containing the exception object.
         */
        for (final ExceptionTable element : exception_array) {
            int handler_pc = element.handler_pc;
            final Instruction handler_pc_ins = getInstructionAt(chain, handler_pc);
            if (handler_pc_ins != null) {
                branchTargets.push(handler_pc_ins, 1);
            }
        }

        int stackDepth = 0;
        Instruction current = chain.start;
        while (current != null) {
            final int opcode = current.opcode;
            final int delta = StackUtils.produceStack(current, constant_pool) - StackUtils.consumeStack(current, constant_pool);
            stackDepth += delta;
            if (stackDepth > max_stack) {
                max_stack = stackDepth;
            }

            // choose the next instruction based on whether current is a branch.
            if (current instanceof BranchInstruction) {
                final BranchInstruction bi = (BranchInstruction) current;
                // for all branches, the target of the branch is pushed on the branch f_stack.
                // conditional branches have a fall through case, selects don't, and
                // jsr/jsr_w return to the next instruction.
                int defaultBranch = bi.getDefaultBranch();
                Instruction target = getInstructionAt(chain, current.pos + defaultBranch);
                branchTargets.push(target, stackDepth);

                if (bi instanceof SelectInstruction) {
                    // explore all of the select's targets. the default target is handled below.
                    final SelectInstruction select = (SelectInstruction) bi;
                    int[] offsets = select.getOffsets();
                    for (final int offset : offsets) {
                        Instruction t = getInstructionAt(chain, current.pos + offset);
                        branchTargets.push(t, stackDepth);
                    }
                    // nothing to fall through to.
                    current = null;
                } else if (!(bi instanceof IfInstruction)) {
                    // if an instruction that comes back to following PC,
                    // push next instruction, with f_stack depth reduced by 1.
                    if (opcode == OpcodeConst.JSR || opcode == OpcodeConst.JSR_W) {
                        branchTargets.push(current.next, stackDepth - 1);
                    }
                    current = null;
                }

            } else {
                // check for instructions that terminate the d_method.
                if (opcode == OpcodeConst.ATHROW || opcode == OpcodeConst.RET
                        || (opcode >= OpcodeConst.IRETURN && opcode <= OpcodeConst.RETURN)) {
                    current = null;
                }
            }

            // normal case, go to the next instruction.
            if (current != null) {
                current = current.next;
            }
            // if we have no more instructions, see if there are any deferred branches to explore.
            if (current == null) {
                final BranchTarget bt = branchTargets.pop();
                if (bt != null) {
                    current = bt.target;
                    stackDepth = bt.stackDepth;
                }
            }
        }

        return max_stack;
    }

    public static Instruction getInstructionAt(InstructionChain chain, int index) {
        Instruction obj = null;
        Instruction current = chain.start;
        while (current != null) {
            if (index == current.pos) {
                obj = current;
                break;
            }
            current = current.next;
        }
        if (obj == null) {
            throw new RuntimeException("getInstructionAt: " + index);
        }
        return obj;
    }
}
