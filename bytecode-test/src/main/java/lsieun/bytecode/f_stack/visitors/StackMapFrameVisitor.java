package lsieun.bytecode.f_stack.visitors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.classfile.attrs.method.Code;
import lsieun.bytecode.classfile.clazz.MethodInfo;
import lsieun.bytecode.cp.*;
import lsieun.bytecode.classfile.utils.AttributeUtils;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.core.type.ArrayType;
import lsieun.bytecode.core.type.ObjectType;
import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.type.UninitializedObjectType;
import lsieun.bytecode.core.utils.AccessFlagUtils;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.BranchInstruction;
import lsieun.bytecode.opcode.facet.CPInstruction;
import lsieun.bytecode.opcode.facet.SelectInstruction;
import lsieun.bytecode.opcode.opcode.*;
import lsieun.bytecode.opcode.utils.InstructionChain;
import lsieun.bytecode.opcode.visitors.OpcodeReadVisitor;
import lsieun.bytecode.utils.AdvancedTypeUtils;
import lsieun.utils.radix.HexUtils;

//[local variable][f_stack]
@SuppressWarnings("Duplicates")
public class StackMapFrameVisitor implements OpcodeVisitor {
    private static final String NO_ARG_FORMAT = "%04d: %-20s || %s\n";
    private static final String ONE_ARG_FORMAT = "%04d: %-15s %-4s || %s\n";
    private static final String NEW_ARRAY_FORMAT = "%04d: %-15s %-4s || %s || %s\n";
    private static final String IINC_FORMAT = "%04d: %-10s %-4s %-4s || %s\n";
    private static final String CP_INS_FORMAT = "%04d: %-15s #%-3s || %s // %s\n";
    private static final String WIDE_LOCAL_VAR_FORMAT = "%04d: wide %-10s %-4d || %s\n";

    private static final Type THIS = new ObjectType("this");

    private ConstantPool constantPool;

    //输入参数-->local variable，
    private boolean isStatic;
    private String descriptor;
    private Code code;
    // Code

    private int max_stack;
    private int max_locals;
    private byte[] code_bytes;
    // StackMapTable
    private Type[] local_variable;
    private Type[] operand_stack;
    private int current_stack;


    public StackMapFrameVisitor() {

    }

    public void visit(MethodInfo methodInfo, ConstantPool constantPool) {
        //第一部分信息，由传入parameter获取
        this.constantPool = constantPool;

        //第二部分信息，由parameter获得

        int accessFlags = methodInfo.access_flags;
        this.isStatic = AccessFlagUtils.isStatic(accessFlags);
        int descriptorIndex = methodInfo.descriptor_index;
        this.descriptor = constantPool.getConstantString(descriptorIndex, CPConst.CONSTANT_Utf8);
        this.code = AttributeUtils.findCodeAttribute(methodInfo);

        //第三部分信息，由第二部分信息中的code获得
        max_stack = code.max_stack;
        max_locals = code.max_locals;
        code_bytes = code.code;

        //第四部分信息，由第三部分的max_stack和max_locals支撑
        operand_stack = new Type[max_stack];
        local_variable = new Type[max_locals];
        current_stack = -1;

        System.out.println("descriptor = " + descriptor);
        System.out.println("isStatic = " + isStatic);
        System.out.println("max_stack = " + max_stack);
        System.out.println("max_locals = " + max_locals);
        System.out.println();

        int idx = 0;
        if (!isStatic) {
            idx++;
            local_variable[0] = THIS;
        }

        Type[] argumentTypes = lsieun.bytecode.core.utils.TypeUtils.getArgumentTypes(descriptor);
        for (int i = 0; i < argumentTypes.length; i++) {
            Type t = argumentTypes[i];
            local_variable[idx + i] = t;
        }

        System.out.println("HexCode: " + HexUtils.fromBytes(code_bytes));
        OpcodeReadVisitor rv = new OpcodeReadVisitor(code_bytes);
        InstructionChain chain = rv.getInstructionChain();

        Map<Integer, State> map = new HashMap<Integer, State>();

        Instruction current = chain.start;
        while (current != null) {
            // （1）恢复原来的Stack状态
            State restoredState = map.get(Integer.valueOf(current.pos));
            if (restoredState != null) {
                System.arraycopy(restoredState.getLocalVariable(), 0, local_variable, 0, max_locals);
                System.arraycopy(restoredState.getOperandStack(), 0, operand_stack, 0, max_stack);
            }

            // (2)处理当前的instruction
            current.accept(this);

            // （3）如果当前指令是跳转指令，那么需要存储当前指令处理后的状态，以便在第（1）处恢复
            if (current instanceof BranchInstruction) {
                BranchInstruction branchIns = (BranchInstruction) current;
                int index = branchIns.getDefaultBranch();
                State state = snapshot();
                map.put(Integer.valueOf(current.pos + index), state);

                if (current instanceof SelectInstruction) {
                    SelectInstruction selectIns = (SelectInstruction) current;
                    int[] indices = selectIns.getOffsets();
                    for (int delta : indices) {
                        State s = snapshot();
                        map.put(Integer.valueOf(current.pos + delta), s);
                    }
                }
            }

            current = current.next;
        }

    }

    private State snapshot() {
        State state = new State(max_stack, max_locals);
        System.arraycopy(operand_stack, 0, state.getOperandStack(), 0, max_stack);
        System.arraycopy(local_variable, 0, state.getLocalVariable(), 0, max_locals);
        return state;
    }

    private static class State {
        private Type[] local_variable;
        private Type[] operand_stack;

        public State(int max_stack, int max_locals) {
            operand_stack = new Type[max_stack];
            local_variable = new Type[max_locals];
        }

        public Type[] getLocalVariable() {
            return local_variable;
        }

        public Type[] getOperandStack() {
            return operand_stack;
        }
    }

    public String getLine() {
        String format = "%s %s";
        Type[] array = new Type[current_stack + 1];
        System.arraycopy(operand_stack, 0, array, 0, current_stack + 1);

        String line = String.format(format, Arrays.toString(local_variable), Arrays.toString(array));
        return line;
    }

    @Override
    public void visitAALOAD(final AALOAD obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        Type type = pop();
        ArrayType at = (ArrayType) type;
        push(at.getElementType());
    }

    @Override
    public void visitAASTORE(final AASTORE obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(3);
    }

    @Override
    public void visitACONST_NULL(final ACONST_NULL obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.NULL);
    }

    @Override
    public void visitICONST_M1(ICONST_M1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitICONST_0(ICONST_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitICONST_1(ICONST_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitICONST_2(ICONST_2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitICONST_3(ICONST_3 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitICONST_4(ICONST_4 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitICONST_5(ICONST_5 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitLCONST_0(LCONST_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLCONST_1(LCONST_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.LONG);
    }

    @Override
    public void visitFCONST_0(FCONST_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFCONST_1(FCONST_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFCONST_2(FCONST_2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitDCONST_0(DCONST_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDCONST_1(DCONST_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitALOAD(final ALOAD obj) {
        // Opcode
        int localVarIndex = obj.index;
        visitONEARGIns(obj, String.valueOf(localVarIndex));

        // StackMapFrame
        Type type = local_variable[localVarIndex];
        push(type);
    }

    @Override
    public void visitILOAD_0(ILOAD_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitILOAD_1(ILOAD_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitILOAD_2(ILOAD_2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitILOAD_3(ILOAD_3 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitLLOAD_0(LLOAD_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLLOAD_1(LLOAD_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLLOAD_2(LLOAD_2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLLOAD_3(LLOAD_3 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.LONG);
    }

    @Override
    public void visitFLOAD_0(FLOAD_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFLOAD_1(FLOAD_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFLOAD_2(FLOAD_2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFLOAD_3(FLOAD_3 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitDLOAD_0(DLOAD_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDLOAD_1(DLOAD_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDLOAD_2(DLOAD_2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDLOAD_3(DLOAD_3 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitALOAD_0(ALOAD_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(THIS);
    }

    @Override
    public void visitALOAD_1(ALOAD_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.OBJECT);
    }

    @Override
    public void visitALOAD_2(ALOAD_2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.OBJECT);
    }

    @Override
    public void visitALOAD_3(ALOAD_3 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.OBJECT);
    }

    @Override
    public void visitANEWARRAY(final ANEWARRAY obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        pop();
        Type type = AdvancedTypeUtils.getANEWARRAY(obj, constantPool);
        ArrayType at = new ArrayType(type, 1);
        push(at);
    }

    @Override
    public void visitARETURN(final ARETURN obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        clearStack();
    }

    @Override
    public void visitARRAYLENGTH(final ARRAYLENGTH obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.INT);
    }

    @Override
    public void visitASTORE(final ASTORE obj) {
        // Opcode
        int localVarIndex = obj.index;
        visitONEARGIns(obj, String.valueOf(localVarIndex));

        // StackMapFrame
        Type type = pop();
        local_variable[localVarIndex] = type;
    }

    @Override
    public void visitISTORE_0(ISTORE_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitISTORE_1(ISTORE_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitISTORE_2(ISTORE_2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitISTORE_3(ISTORE_3 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitLSTORE_0(LSTORE_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLSTORE_1(LSTORE_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLSTORE_2(LSTORE_2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLSTORE_3(LSTORE_3 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.LONG);
    }

    @Override
    public void visitFSTORE_0(FSTORE_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFSTORE_1(FSTORE_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFSTORE_2(FSTORE_2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFSTORE_3(FSTORE_3 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitDSTORE_0(DSTORE_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDSTORE_1(DSTORE_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDSTORE_2(DSTORE_2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDSTORE_3(DSTORE_3 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitASTORE_0(ASTORE_0 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.OBJECT);
    }

    @Override
    public void visitASTORE_1(ASTORE_1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.OBJECT);
    }

    @Override
    public void visitASTORE_2(ASTORE_2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.OBJECT);
    }

    @Override
    public void visitASTORE_3(ASTORE_3 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        push(TypeUtils.OBJECT);
    }

    @Override
    public void visitATHROW(final ATHROW obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        Type type = pop();
        clearStack();
        push(type);
    }

    @Override
    public void visitBALOAD(final BALOAD obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitBASTORE(final BASTORE obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(3);
    }

    @Override
    public void visitBIPUSH(final BIPUSH obj) {
        // Opcode
        Number value = obj.getValue();
        visitONEARGIns(obj, String.valueOf(value));

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitCALOAD(final CALOAD obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitCASTORE(final CASTORE obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(3);
    }

    @Override
    public void visitCHECKCAST(final CHECKCAST obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        pop();
        Type type = AdvancedTypeUtils.getCHECKCAST(obj, constantPool);
        push(type);
    }

    @Override
    public void visitD2F(final D2F obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitD2I(final D2I obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.INT);
    }

    @Override
    public void visitD2L(final D2L obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.LONG);
    }

    @Override
    public void visitDADD(final DADD obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDALOAD(final DALOAD obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDASTORE(final DASTORE obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(3);
    }

    @Override
    public void visitDCMPG(final DCMPG obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitDCMPL(final DCMPL obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitDDIV(final DDIV obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDLOAD(final DLOAD obj) {
        // Opcode
        int localVarIndex = obj.index;
        visitONEARGIns(obj, String.valueOf(localVarIndex));

        // StackMapFrame
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDMUL(final DMUL obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDNEG(final DNEG obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDREM(final DREM obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDRETURN(final DRETURN obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        clearStack();
    }

    @Override
    public void visitDSTORE(final DSTORE obj) {
        // Opcode
        int localVarIndex = obj.index;
        visitONEARGIns(obj, String.valueOf(localVarIndex));

        // StackMapFrame
        pop();
        local_variable[localVarIndex] = TypeUtils.DOUBLE;
        local_variable[localVarIndex + 1] = TypeUtils.UNKNOWN;
    }

    @Override
    public void visitDSUB(final DSUB obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDUP(final DUP obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        Type type = pop();
        push(type);
        push(type);
    }

    @Override
    public void visitDUP_X1(final DUP_X1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        Type t1 = pop();
        Type t2 = pop();
        push(t1);
        push(t2);
        push(t1);
    }

    @Override
    public void visitDUP_X2(final DUP_X2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        Type t1 = pop();
        Type t2 = pop();
        Type t3 = pop();
        push(t1);
        push(t3);
        push(t2);
        push(t1);
    }

    @Override
    public void visitDUP2(final DUP2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        Type t1 = pop();
        Type t2 = pop();
        push(t2);
        push(t1);
        push(t2);
        push(t1);
    }

    @Override
    public void visitDUP2_X1(final DUP2_X1 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        Type t1 = pop();
        Type t2 = pop();
        Type t3 = pop();
        push(t2);
        push(t1);
        push(t3);
        push(t2);
        push(t1);
    }

    @Override
    public void visitDUP2_X2(final DUP2_X2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        Type t1 = pop();
        Type t2 = pop();
        Type t3 = pop();
        Type t4 = pop();
        push(t2);
        push(t1);
        push(t4);
        push(t3);
        push(t2);
        push(t1);
    }

    @Override
    public void visitF2D(final F2D obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitF2I(final F2I obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.INT);
    }

    @Override
    public void visitF2L(final F2L obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.LONG);
    }

    @Override
    public void visitFADD(final FADD obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFALOAD(final FALOAD obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFASTORE(final FASTORE obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(3);
    }

    @Override
    public void visitFCMPG(final FCMPG obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitFCMPL(final FCMPL obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitFDIV(final FDIV obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFLOAD(final FLOAD obj) {
        // Opcode
        int localVarIndex = obj.index;
        visitONEARGIns(obj, String.valueOf(localVarIndex));

        // StackMapFrame
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFMUL(final FMUL obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFNEG(final FNEG obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFREM(final FREM obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFRETURN(final FRETURN obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        clearStack();
    }

    @Override
    public void visitFSTORE(final FSTORE obj) {
        // Opcode
        int localVarIndex = obj.index;
        visitONEARGIns(obj, String.valueOf(localVarIndex));

        // StackMapFrame
        local_variable[localVarIndex] = TypeUtils.FLOAT;
    }

    @Override
    public void visitFSUB(final FSUB obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitGETFIELD(final GETFIELD obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        pop();
        Type type = AdvancedTypeUtils.getGETFIELD(obj, constantPool);
        push(type);
    }

    @Override
    public void visitGETSTATIC(final GETSTATIC obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        Type type = AdvancedTypeUtils.getGETSTATIC(obj, constantPool);
        push(type);
    }

    @Override
    public void visitGOTO(final GOTO obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // no f_stack changes.
    }

    @Override
    public void visitGOTO_W(final GOTO_W obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // no f_stack changes.
    }

    @Override
    public void visitI2B(final I2B obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.INT);
    }

    @Override
    public void visitI2C(final I2C obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.INT);
    }

    @Override
    public void visitI2D(final I2D obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitI2F(final I2F obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitI2L(final I2L obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.LONG);
    }

    @Override
    public void visitI2S(final I2S obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.INT);
    }

    @Override
    public void visitIADD(final IADD obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitIALOAD(final IALOAD obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitIAND(final IAND obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitIASTORE(final IASTORE obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(3);
    }

    @Override
    public void visitIDIV(final IDIV obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitIF_ACMPEQ(final IF_ACMPEQ obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop(2);
    }

    @Override
    public void visitIF_ACMPNE(final IF_ACMPNE obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop(2);
    }

    @Override
    public void visitIF_ICMPEQ(final IF_ICMPEQ obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop(2);
    }

    @Override
    public void visitIF_ICMPGE(final IF_ICMPGE obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop(2);
    }

    @Override
    public void visitIF_ICMPGT(final IF_ICMPGT obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop(2);
    }

    @Override
    public void visitIF_ICMPLE(final IF_ICMPLE obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop(2);
    }

    @Override
    public void visitIF_ICMPLT(final IF_ICMPLT obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop(2);
    }

    @Override
    public void visitIF_ICMPNE(final IF_ICMPNE obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop(2);
    }

    @Override
    public void visitIFEQ(final IFEQ obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop();
    }

    @Override
    public void visitIFGE(final IFGE obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop();
    }

    @Override
    public void visitIFGT(final IFGT obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop();
    }

    @Override
    public void visitIFLE(final IFLE obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop();
    }

    @Override
    public void visitIFLT(final IFLT obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop();
    }

    @Override
    public void visitIFNE(final IFNE obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop();
    }

    @Override
    public void visitIFNONNULL(final IFNONNULL obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop();
    }

    @Override
    public void visitIFNULL(final IFNULL obj) {
        // Opcode
        int offset = obj.branch;
        visitONEARGIns(obj, String.valueOf(offset));

        // StackMapFrame
        pop();
    }

    @Override
    public void visitIINC(final IINC obj) {
        String name = OpcodeConst.getOpcodeName(obj.opcode);
        int localVarIndex = obj.index;

        int increment = obj.constValue;
        System.out.printf(IINC_FORMAT, obj.pos, name, localVarIndex, increment, getLine());

        // f_stack is not changed.
    }

    @Override
    public void visitILOAD(final ILOAD obj) {
        // Opcode
        int localVarIndex = obj.index;
        visitONEARGIns(obj, String.valueOf(localVarIndex));

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitIMUL(final IMUL obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitINEG(final INEG obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.INT);
    }

    @Override
    public void visitINSTANCEOF(final INSTANCEOF obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.INT);
    }

    @Override
    public void visitINVOKEDYNAMIC(final INVOKEDYNAMIC obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        int n = AdvancedTypeUtils.getArgumentTypes(obj, constantPool).length;
        pop(n);

        Type returnType = AdvancedTypeUtils.getReturnType(obj, constantPool);
        if(returnType != TypeUtils.VOID) {
            push(returnType);
        }
    }

    @Override
    public void visitINVOKEINTERFACE(final INVOKEINTERFACE obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        pop();
        int n = AdvancedTypeUtils.getArgumentTypes(obj, constantPool).length;
        pop(n);

        Type returnType = AdvancedTypeUtils.getReturnType(obj, constantPool);
        if(returnType != TypeUtils.VOID) {
            push(returnType);
        }
    }

    @Override
    public void visitINVOKESPECIAL(INVOKESPECIAL obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        // TODO: 这里init的判断逻辑，我考虑的还并不成熟，
        //  至少有两种情况：（1）在自己的调用自己的构造函数或调用父类的构造函数；（2）创建别的对象
        String methodName = AdvancedTypeUtils.getMethodName(obj, constantPool);
        if("<init>".equals(methodName)) {
            Type type = operand_stack[current_stack];
            if(type instanceof UninitializedObjectType) {
                UninitializedObjectType t = (UninitializedObjectType) type;
                ObjectType objectType = t.getInitialized();
                operand_stack[current_stack] = objectType;
            }
        }
        pop();
        int n = AdvancedTypeUtils.getArgumentTypes(obj, constantPool).length;
        pop(n);


        Type returnType = AdvancedTypeUtils.getReturnType(obj, constantPool);
        if(returnType != TypeUtils.VOID) {
            push(returnType);
        }
    }

    @Override
    public void visitINVOKESTATIC(INVOKESTATIC obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        int n = AdvancedTypeUtils.getArgumentTypes(obj, constantPool).length;
        pop(n);

        Type returnType = AdvancedTypeUtils.getReturnType(obj, constantPool);
        if(returnType != TypeUtils.VOID) {
            push(returnType);
        }
    }

    @Override
    public void visitINVOKEVIRTUAL(INVOKEVIRTUAL obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        pop();
        int n = AdvancedTypeUtils.getArgumentTypes(obj, constantPool).length;
        pop(n);

        Type returnType = AdvancedTypeUtils.getReturnType(obj, constantPool);
        if(returnType != TypeUtils.VOID) {
            push(returnType);
        }
    }

    @Override
    public void visitIOR(final IOR obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitIREM(final IREM obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitIRETURN(final IRETURN obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        clearStack();
    }

    @Override
    public void visitISHL(final ISHL obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitISHR(final ISHR obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitISTORE(final ISTORE obj) {
        // Opcode
        int localVarIndex = obj.index;
        visitONEARGIns(obj, String.valueOf(localVarIndex));

        // StackMapFrame
        pop();
        local_variable[localVarIndex] = TypeUtils.INT;
    }

    @Override
    public void visitISUB(final ISUB obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitIUSHR(final IUSHR obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitIXOR(final IXOR obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitJSR(final JSR obj) {
//        Type type = new ReturnaddressType(obj.physicalSuccessor());
//        push(type);
    }

    @Override
    public void visitJSR_W(final JSR_W obj) {
//        Type type = new ReturnaddressType(obj.physicalSuccessor());
//        push(type);
    }

    @Override
    public void visitL2D(final L2D obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitL2F(final L2F obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.FLOAT);
    }

    @Override
    public void visitL2I(final L2I obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.INT);
    }

    @Override
    public void visitLADD(final LADD obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLALOAD(final LALOAD obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLAND(final LAND obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLASTORE(final LASTORE obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(3);
    }

    @Override
    public void visitLCMP(final LCMP obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitLDC(final LDC obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        final Constant c = constantPool.getConstant(obj.getIndex());
        if (c instanceof ConstantInteger) {
            push(TypeUtils.INT);
        }
        if (c instanceof ConstantFloat) {
            push(TypeUtils.FLOAT);
        }
        if (c instanceof ConstantString) {
            push(TypeUtils.STRING);
        }
        if (c instanceof ConstantClass) {
            push(TypeUtils.CLASS);
        }
    }

    public void visitLDC_W(final LDC_W obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        final Constant c = constantPool.getConstant(obj.getIndex());
        if (c instanceof ConstantInteger) {
            push(TypeUtils.INT);
        }
        if (c instanceof ConstantFloat) {
            push(TypeUtils.FLOAT);
        }
        if (c instanceof ConstantString) {
            push(TypeUtils.STRING);
        }
        if (c instanceof ConstantClass) {
            push(TypeUtils.CLASS);
        }
    }

    @Override
    public void visitLDC2_W(final LDC2_W obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        final Constant c = constantPool.getConstant(obj.getIndex());
        if (c instanceof ConstantLong) {
            push(TypeUtils.LONG);
        }
        if (c instanceof ConstantDouble) {
            push(TypeUtils.DOUBLE);
        }
    }

    @Override
    public void visitLDIV(final LDIV obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLLOAD(final LLOAD obj) {
        // Opcode
        int localVarIndex = obj.index;
        visitONEARGIns(obj, String.valueOf(localVarIndex));

        // StackMapFrame
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLMUL(final LMUL obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLNEG(final LNEG obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLOOKUPSWITCH(final LOOKUPSWITCH obj) {
        // Opcode
        String name = OpcodeConst.getOpcodeName(obj.opcode);
        int branch_offset = obj.default_branch;
        System.out.printf(ONE_ARG_FORMAT, obj.pos, name, branch_offset, getLine());
        int match_length = obj.npairs;
        int[] matches = obj.matches;
        int[] offsets = obj.offsets;
        String prefix = "      ";
        System.out.println(prefix + "{");
        for (int i = 0; i < match_length; i++) {
            String format = "%9s: %s";
            String line = String.format(format, matches[i], offsets[i]);
            System.out.println(prefix + line);
        }
        System.out.println(prefix + "  default: " + branch_offset);
        System.out.println(prefix + "}");

        // StackMapFrame
        pop();
    }

    @Override
    public void visitLOR(final LOR obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLREM(final LREM obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLRETURN(final LRETURN obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        clearStack();
    }

    @Override
    public void visitLSHL(final LSHL obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLSHR(final LSHR obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLSTORE(final LSTORE obj) {
        // Opcode
        int localVarIndex = obj.index;
        visitONEARGIns(obj, String.valueOf(localVarIndex));

        // StackMapFrame
        local_variable[localVarIndex] = TypeUtils.LONG;
        local_variable[localVarIndex + 1] = TypeUtils.UNKNOWN;
    }

    @Override
    public void visitLSUB(final LSUB obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLUSHR(final LUSHR obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.LONG);
    }

    @Override
    public void visitLXOR(final LXOR obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.LONG);
    }

    @Override
    public void visitMONITORENTER(final MONITORENTER obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
    }

    @Override
    public void visitMONITOREXIT(final MONITOREXIT obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
    }

    @Override
    public void visitWIDE(WIDE obj) {
        // Opcode
        visitNOARGIns(obj);
    }

    @Override
    public void visitBREAKPOINT(BREAKPOINT obj) {

    }

    @Override
    public void visitIMPDEP1(IMPDEP1 obj) {

    }

    @Override
    public void visitIMPDEP2(IMPDEP2 obj) {

    }

    @Override
    public void visitMULTIANEWARRAY(final MULTIANEWARRAY obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        for (int i = 0; i < obj.dimensions; i++) {
            pop();
        }
        Type type = AdvancedTypeUtils.getMULTIANEWARRAY(obj, constantPool);
        push(type);
    }

    @Override
    public void visitNEW(final NEW obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        Type type = AdvancedTypeUtils.getNEW(obj, constantPool);
        push(new UninitializedObjectType((ObjectType) (type)));
    }

    @Override
    public void visitNEWARRAY(final NEWARRAY obj) {
        // Opcode
        String name = OpcodeConst.getOpcodeName(obj.opcode);
        byte atype = obj.atype;
        Type type = TypeUtils.getType(atype);
        System.out.printf(NEW_ARRAY_FORMAT, obj.pos, name, atype, getLine(), type);

        // StackMapFrame
        pop();
        push(type);
    }

    @Override
    public void visitNOP(final NOP obj) {
        // Opcode
        visitNOARGIns(obj);
    }

    @Override
    public void visitPOP(final POP obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop();
    }

    @Override
    public void visitPOP2(final POP2 obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        final Type t = pop();
        if (t.getSize() == 1) {
            pop();
        }
    }

    @Override
    public void visitPUTFIELD(final PUTFIELD obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        pop(2);
    }

    @Override
    public void visitPUTSTATIC(final PUTSTATIC obj) {
        // Opcode
        visitCPInstruction(obj);

        // StackMapFrame
        pop();
    }

    @Override
    public void visitRET(final RET o) {
        // do nothing, return address
        // is in in the local variables.
    }

    @Override
    public void visitRETURN(final RETURN obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        clearStack();
    }

    @Override
    public void visitSALOAD(final SALOAD obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(2);
        push(TypeUtils.INT);
    }

    @Override
    public void visitSASTORE(final SASTORE obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        pop(3);
    }

    @Override
    public void visitSIPUSH(final SIPUSH obj) {
        // Opcode
        Number value = obj.getValue();
        visitONEARGIns(obj, String.valueOf(value));

        // StackMapFrame
        push(TypeUtils.INT);
    }

    @Override
    public void visitSWAP(final SWAP obj) {
        // Opcode
        visitNOARGIns(obj);

        // StackMapFrame
        final Type t = pop();
        final Type u = pop();
        push(t);
        push(u);
    }

    @Override
    public void visitTABLESWITCH(final TABLESWITCH obj) {
        // Opcode
        String name = OpcodeConst.getOpcodeName(obj.opcode);
        int branch_offset = obj.default_branch;
        System.out.printf(ONE_ARG_FORMAT, obj.pos, name, branch_offset, getLine());
        int match_length = obj.high - obj.low + 1;
        int[] matches = obj.matches;
        int[] offsets = obj.offsets;
        String prefix = "      ";
        System.out.println(prefix + "{");
        for (int i = 0; i < match_length; i++) {
            String format = "%9s: %s";
            String line = String.format(format, matches[i], offsets[i]);
            System.out.println(prefix + line);
        }
        System.out.println(prefix + "  default: " + branch_offset);
        System.out.println(prefix + "}");

        // StackMapFrame
        pop();
    }


    // region auxiliary
    private void visitNOARGIns(Instruction obj) {
        String name = OpcodeConst.getOpcodeName(obj.opcode);
        System.out.printf(NO_ARG_FORMAT, obj.pos, name, getLine());
    }

    private void visitONEARGIns(Instruction obj, String firstArg) {
        String name = OpcodeConst.getOpcodeName(obj.opcode);
        System.out.printf(ONE_ARG_FORMAT, obj.pos, name, firstArg, getLine());
    }

    public void visitCPInstruction(CPInstruction obj) {
        Instruction ins = (Instruction) obj;
        String name = OpcodeConst.getOpcodeName(ins.opcode);
        int cpIndex = obj.getIndex();
        Constant constant = constantPool.getConstant(cpIndex);
        String cpValue = constant.value;

        System.out.printf(CP_INS_FORMAT, ins.pos, name, cpIndex, getLine(), cpValue);
    }

    private void visitWIDEARGIns(Instruction obj, String firstArg) {
        String name = OpcodeConst.getOpcodeName(obj.opcode);
        System.out.printf(WIDE_LOCAL_VAR_FORMAT, obj.pos, name, firstArg, getLine());
    }

    // endregion


    // region f_stack operation
    public void push(Type type) {
        int size = type.getSize();
        for (int i = 0; i < size; i++) {
            current_stack++;
            operand_stack[current_stack] = type;
        }
    }

    public Type pop() {
        Type type = operand_stack[current_stack];
        int size = type.getSize();
        for (int i = 0; i < size; i++) {
            operand_stack[current_stack] = null;
            current_stack--;
        }
        return type;
    }

    public void pop(int n) {
        for (int i = 0; i < n; i++) {
            pop();
        }
    }

    public void clearStack() {
        while (current_stack >= 0) {
            operand_stack[current_stack] = null;
            current_stack--;
        }
    }
    // endregion
}
