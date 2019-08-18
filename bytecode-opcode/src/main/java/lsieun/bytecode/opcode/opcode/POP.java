package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.PopInstruction;
import lsieun.bytecode.opcode.facet.StackInstruction;

/**
 * POP - Pop top operand f_stack word
 *
 * <PRE>Stack: ..., word -&gt; ...</PRE>
 */
public class POP extends Instruction implements StackInstruction, PopInstruction {

    public POP() {
        super(OpcodeConst.POP, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitPOP(this);
    }

}
