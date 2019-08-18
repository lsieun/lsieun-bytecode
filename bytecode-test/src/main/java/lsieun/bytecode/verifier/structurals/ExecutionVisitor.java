package lsieun.bytecode.verifier.structurals;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.cp.*;
import lsieun.bytecode.core.cst.JVMConst;
import lsieun.bytecode.core.type.*;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.opcode.*;
import lsieun.bytecode.utils.AdvancedTypeUtils;

public class ExecutionVisitor implements OpcodeVisitor {
    /**
     * The executionframe we're operating on.
     */
    private Frame frame = null;

    /**
     * The ConstantPoolGen we're working with.
     */
    private ConstantPool constantPool = null;

    /**
     * Constructor. Constructs a new instance of this class.
     */
    public ExecutionVisitor() {
    }

    /**
     * The OperandStack from the current Frame we're operating on.
     *
     * @see #setFrame(Frame)
     */
    private OperandStack stack() {
        return frame.getStack();
    }

    /**
     * The LocalVariables from the current Frame we're operating on.
     *
     * @see #setFrame(Frame)
     */
    private LocalVariables locals() {
        return frame.getLocals();
    }

    /**
     * Sets the ConstantPoolGen needed for symbolic execution.
     */
    public void setConstantPool(final ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    /**
     * The only d_method granting access to the single instance of
     * the ExecutionVisitor class. Before actively using this
     * instance, <B>SET THE ConstantPoolGen FIRST</B>.
     */
    public void setFrame(final Frame f) {
        this.frame = f;
    }

    @Override
    public void visitAALOAD(final AALOAD obj) {
        stack().pop();                                                        // pop the index int
        //System.out.print(f_stack().peek());
        final Type t = stack().pop(); // Pop Array type
        if (t == TypeUtils.NULL) {
            stack().push(TypeUtils.NULL);
        }    // Do nothing stackwise --- a NullPointerException is thrown at Run-Time
        else {
            final ArrayType at = (ArrayType) t;
            stack().push(at.getElementType());
        }
    }

    @Override
    public void visitAASTORE(final AASTORE obj) {
        stack().pop();
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitACONST_NULL(final ACONST_NULL obj) {
        stack().push(TypeUtils.NULL);
    }

    @Override
    public void visitICONST_M1(ICONST_M1 obj) {
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitICONST_0(ICONST_0 obj) {
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitICONST_1(ICONST_1 obj) {
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitICONST_2(ICONST_2 obj) {
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitICONST_3(ICONST_3 obj) {
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitICONST_4(ICONST_4 obj) {
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitICONST_5(ICONST_5 obj) {
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitLCONST_0(LCONST_0 obj) {
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLCONST_1(LCONST_1 obj) {
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitFCONST_0(FCONST_0 obj) {
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFCONST_1(FCONST_1 obj) {
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFCONST_2(FCONST_2 obj) {
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitDCONST_0(DCONST_0 obj) {
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDCONST_1(DCONST_1 obj) {
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitALOAD(final ALOAD obj) {
        stack().push(locals().get(obj.getIndex()));
    }

    @Override
    public void visitILOAD_0(ILOAD_0 obj) {

    }

    @Override
    public void visitILOAD_1(ILOAD_1 obj) {

    }

    @Override
    public void visitILOAD_2(ILOAD_2 obj) {

    }

    @Override
    public void visitILOAD_3(ILOAD_3 obj) {

    }

    @Override
    public void visitLLOAD_0(LLOAD_0 obj) {

    }

    @Override
    public void visitLLOAD_1(LLOAD_1 obj) {

    }

    @Override
    public void visitLLOAD_2(LLOAD_2 obj) {

    }

    @Override
    public void visitLLOAD_3(LLOAD_3 obj) {

    }

    @Override
    public void visitFLOAD_0(FLOAD_0 obj) {

    }

    @Override
    public void visitFLOAD_1(FLOAD_1 obj) {

    }

    @Override
    public void visitFLOAD_2(FLOAD_2 obj) {

    }

    @Override
    public void visitFLOAD_3(FLOAD_3 obj) {

    }

    @Override
    public void visitDLOAD_0(DLOAD_0 obj) {

    }

    @Override
    public void visitDLOAD_1(DLOAD_1 obj) {

    }

    @Override
    public void visitDLOAD_2(DLOAD_2 obj) {

    }

    @Override
    public void visitDLOAD_3(DLOAD_3 obj) {

    }

    @Override
    public void visitALOAD_0(ALOAD_0 obj) {

    }

    @Override
    public void visitALOAD_1(ALOAD_1 obj) {

    }

    @Override
    public void visitALOAD_2(ALOAD_2 obj) {

    }

    @Override
    public void visitALOAD_3(ALOAD_3 obj) {

    }

    @Override
    public void visitANEWARRAY(final ANEWARRAY obj) {
        stack().pop(); //count
        Type type = AdvancedTypeUtils.getANEWARRAY(obj, constantPool);
        stack().push(new ArrayType(type, 1));
    }

    @Override
    public void visitARETURN(final ARETURN obj) {
        stack().pop();
    }

    @Override
    public void visitARRAYLENGTH(final ARRAYLENGTH obj) {
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitASTORE(final ASTORE obj) {
        locals().set(obj.getIndex(), stack().pop());
        //System.err.println("TODO-DEBUG:    set LV '"+o.getIndex()+"' to '"+locals().get(o.getIndex())+"'.");
    }

    @Override
    public void visitISTORE_0(ISTORE_0 obj) {

    }

    @Override
    public void visitISTORE_1(ISTORE_1 obj) {

    }

    @Override
    public void visitISTORE_2(ISTORE_2 obj) {

    }

    @Override
    public void visitISTORE_3(ISTORE_3 obj) {

    }

    @Override
    public void visitLSTORE_0(LSTORE_0 obj) {

    }

    @Override
    public void visitLSTORE_1(LSTORE_1 obj) {

    }

    @Override
    public void visitLSTORE_2(LSTORE_2 obj) {

    }

    @Override
    public void visitLSTORE_3(LSTORE_3 obj) {

    }

    @Override
    public void visitFSTORE_0(FSTORE_0 obj) {

    }

    @Override
    public void visitFSTORE_1(FSTORE_1 obj) {

    }

    @Override
    public void visitFSTORE_2(FSTORE_2 obj) {

    }

    @Override
    public void visitFSTORE_3(FSTORE_3 obj) {

    }

    @Override
    public void visitDSTORE_0(DSTORE_0 obj) {

    }

    @Override
    public void visitDSTORE_1(DSTORE_1 obj) {

    }

    @Override
    public void visitDSTORE_2(DSTORE_2 obj) {

    }

    @Override
    public void visitDSTORE_3(DSTORE_3 obj) {

    }

    @Override
    public void visitASTORE_0(ASTORE_0 obj) {

    }

    @Override
    public void visitASTORE_1(ASTORE_1 obj) {

    }

    @Override
    public void visitASTORE_2(ASTORE_2 obj) {

    }

    @Override
    public void visitASTORE_3(ASTORE_3 obj) {

    }

    @Override
    public void visitATHROW(final ATHROW obj) {
        final Type t = stack().pop();
        stack().clear();
        if (t.equals(TypeUtils.NULL)) {
            stack().push(TypeUtils.getType("Ljava/lang/NullPointerException;"));
        } else {
            stack().push(t);
        }
    }

    @Override
    public void visitBALOAD(final BALOAD obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitBASTORE(final BASTORE obj) {
        stack().pop();
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitBIPUSH(final BIPUSH obj) {
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitCALOAD(final CALOAD obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitCASTORE(final CASTORE obj) {
        stack().pop();
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitCHECKCAST(final CHECKCAST obj) {
        // It's possibly wrong to do so, but SUN's
        // ByteCode verifier seems to do (only) this, too.
        // TODO: One could use a sophisticated analysis here to check
        //       if a type cannot possibly be cated to another and by
        //       so doing predict the ClassCastException at run-time.
        stack().pop();
        Type type = AdvancedTypeUtils.getCHECKCAST(obj, constantPool);
        stack().push(type);
    }

    @Override
    public void visitD2F(final D2F obj) {
        stack().pop();
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitD2I(final D2I obj) {
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitD2L(final D2L obj) {
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitDADD(final DADD obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDALOAD(final DALOAD obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDASTORE(final DASTORE obj) {
        stack().pop();
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitDCMPG(final DCMPG obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitDCMPL(final DCMPL obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitDDIV(final DDIV obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDLOAD(final DLOAD obj) {
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDMUL(final DMUL obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDNEG(final DNEG obj) {
        stack().pop();
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDREM(final DREM obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDRETURN(final DRETURN obj) {
        stack().pop();
    }

    @Override
    public void visitDSTORE(final DSTORE obj) {
        locals().set(obj.getIndex(), stack().pop());
        locals().set(obj.getIndex() + 1, TypeUtils.UNKNOWN);
    }

    @Override
    public void visitDSUB(final DSUB obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDUP(final DUP obj) {
        final Type t = stack().pop();
        stack().push(t);
        stack().push(t);
    }

    @Override
    public void visitDUP_X1(final DUP_X1 obj) {
        final Type w1 = stack().pop();
        final Type w2 = stack().pop();
        stack().push(w1);
        stack().push(w2);
        stack().push(w1);
    }

    @Override
    public void visitDUP_X2(final DUP_X2 obj) {
        final Type w1 = stack().pop();
        final Type w2 = stack().pop();
        if (w2.getSize() == 2) {
            stack().push(w1);
            stack().push(w2);
            stack().push(w1);
        } else {
            final Type w3 = stack().pop();
            stack().push(w1);
            stack().push(w3);
            stack().push(w2);
            stack().push(w1);
        }
    }

    @Override
    public void visitDUP2(final DUP2 obj) {
        final Type t = stack().pop();
        if (t.getSize() == 2) {
            stack().push(t);
            stack().push(t);
        } else { // t.getSize() is 1
            final Type u = stack().pop();
            stack().push(u);
            stack().push(t);
            stack().push(u);
            stack().push(t);
        }
    }

    @Override
    public void visitDUP2_X1(final DUP2_X1 obj) {
        final Type t = stack().pop();
        if (t.getSize() == 2) {
            final Type u = stack().pop();
            stack().push(t);
            stack().push(u);
            stack().push(t);
        } else { //t.getSize() is1
            final Type u = stack().pop();
            final Type v = stack().pop();
            stack().push(u);
            stack().push(t);
            stack().push(v);
            stack().push(u);
            stack().push(t);
        }
    }

    @Override
    public void visitDUP2_X2(final DUP2_X2 obj) {
        final Type t = stack().pop();
        if (t.getSize() == 2) {
            final Type u = stack().pop();
            if (u.getSize() == 2) {
                stack().push(t);
                stack().push(u);
                stack().push(t);
            } else {
                final Type v = stack().pop();
                stack().push(t);
                stack().push(v);
                stack().push(u);
                stack().push(t);
            }
        } else { //t.getSize() is 1
            final Type u = stack().pop();
            final Type v = stack().pop();
            if (v.getSize() == 2) {
                stack().push(u);
                stack().push(t);
                stack().push(v);
                stack().push(u);
                stack().push(t);
            } else {
                final Type w = stack().pop();
                stack().push(u);
                stack().push(t);
                stack().push(w);
                stack().push(v);
                stack().push(u);
                stack().push(t);
            }
        }
    }

    @Override
    public void visitF2D(final F2D obj) {
        stack().pop();
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitF2I(final F2I obj) {
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitF2L(final F2L obj) {
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitFADD(final FADD obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFALOAD(final FALOAD obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFASTORE(final FASTORE obj) {
        stack().pop();
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitFCMPG(final FCMPG obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitFCMPL(final FCMPL obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitFDIV(final FDIV obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFLOAD(final FLOAD obj) {
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFMUL(final FMUL obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFNEG(final FNEG obj) {
        stack().pop();
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFREM(final FREM obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitFRETURN(final FRETURN obj) {
        stack().pop();
    }

    @Override
    public void visitFSTORE(final FSTORE obj) {
        locals().set(obj.getIndex(), stack().pop());
    }

    @Override
    public void visitFSUB(final FSUB obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitGETFIELD(final GETFIELD obj) {
        stack().pop();
        Type t = AdvancedTypeUtils.getGETFIELD(obj, constantPool);
        if (t.equals(TypeUtils.BOOLEAN) ||
                t.equals(TypeUtils.CHAR) ||
                t.equals(TypeUtils.BYTE) ||
                t.equals(TypeUtils.SHORT)) {
            t = TypeUtils.INT;
        }
        stack().push(t);
    }

    @Override
    public void visitGETSTATIC(final GETSTATIC obj) {
        Type t = AdvancedTypeUtils.getGETSTATIC(obj, constantPool);
        if (t.equals(TypeUtils.BOOLEAN) ||
                t.equals(TypeUtils.CHAR) ||
                t.equals(TypeUtils.BYTE) ||
                t.equals(TypeUtils.SHORT)) {
            t = TypeUtils.INT;
        }
        stack().push(t);
    }

    @Override
    public void visitGOTO(final GOTO obj) {
        // no f_stack changes.
    }

    @Override
    public void visitGOTO_W(final GOTO_W obj) {
        // no f_stack changes.
    }

    @Override
    public void visitI2B(final I2B obj) {
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitI2C(final I2C obj) {
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitI2D(final I2D obj) {
        stack().pop();
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitI2F(final I2F obj) {
        stack().pop();
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitI2L(final I2L obj) {
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitI2S(final I2S obj) {
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitIADD(final IADD obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitIALOAD(final IALOAD obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitIAND(final IAND obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitIASTORE(final IASTORE obj) {
        stack().pop();
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitIDIV(final IDIV obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitIF_ACMPEQ(final IF_ACMPEQ obj) {
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitIF_ACMPNE(final IF_ACMPNE obj) {
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitIF_ICMPEQ(final IF_ICMPEQ obj) {
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitIF_ICMPGE(final IF_ICMPGE obj) {
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitIF_ICMPGT(final IF_ICMPGT obj) {
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitIF_ICMPLE(final IF_ICMPLE obj) {
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitIF_ICMPLT(final IF_ICMPLT obj) {
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitIF_ICMPNE(final IF_ICMPNE obj) {
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitIFEQ(final IFEQ obj) {
        stack().pop();
    }

    @Override
    public void visitIFGE(final IFGE obj) {
        stack().pop();
    }

    @Override
    public void visitIFGT(final IFGT obj) {
        stack().pop();
    }

    @Override
    public void visitIFLE(final IFLE obj) {
        stack().pop();
    }

    @Override
    public void visitIFLT(final IFLT obj) {
        stack().pop();
    }

    @Override
    public void visitIFNE(final IFNE obj) {
        stack().pop();
    }

    @Override
    public void visitIFNONNULL(final IFNONNULL obj) {
        stack().pop();
    }

    @Override
    public void visitIFNULL(final IFNULL obj) {
        stack().pop();
    }

    @Override
    public void visitIINC(final IINC obj) {
        // f_stack is not changed.
    }

    @Override
    public void visitILOAD(final ILOAD obj) {
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitIMUL(final IMUL obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitINEG(final INEG obj) {
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitINSTANCEOF(final INSTANCEOF obj) {
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitINVOKEDYNAMIC(final INVOKEDYNAMIC obj) {
        for (int i = 0; i < AdvancedTypeUtils.getArgumentTypes(obj, constantPool).length; i++) {
            stack().pop();
        }
        // We are sure the invoked d_method will xRETURN eventually
        // We simulate xRETURNs functionality here because we
        // don't really "jump into" and simulate the invoked
        // d_method.
        if (AdvancedTypeUtils.getReturnType(obj, constantPool) != TypeUtils.VOID) {
            Type t = AdvancedTypeUtils.getReturnType(obj, constantPool);
            if (t.equals(TypeUtils.BOOLEAN) ||
                    t.equals(TypeUtils.CHAR) ||
                    t.equals(TypeUtils.BYTE) ||
                    t.equals(TypeUtils.SHORT)) {
                t = TypeUtils.INT;
            }
            stack().push(t);
        }
    }

    @Override
    public void visitINVOKEINTERFACE(final INVOKEINTERFACE obj) {
        stack().pop();    //objectref
        for (int i = 0; i < AdvancedTypeUtils.getArgumentTypes(obj, constantPool).length; i++) {
            stack().pop();
        }
        // We are sure the invoked d_method will xRETURN eventually
        // We simulate xRETURNs functionality here because we
        // don't really "jump into" and simulate the invoked
        // d_method.
        if (AdvancedTypeUtils.getReturnType(obj, constantPool) != TypeUtils.VOID) {
            Type t = AdvancedTypeUtils.getReturnType(obj, constantPool);
            if (t.equals(TypeUtils.BOOLEAN) ||
                    t.equals(TypeUtils.CHAR) ||
                    t.equals(TypeUtils.BYTE) ||
                    t.equals(TypeUtils.SHORT)) {
                t = TypeUtils.INT;
            }
            stack().push(t);
        }
    }

    @Override
    public void visitINVOKESPECIAL(final INVOKESPECIAL obj) {
        if (AdvancedTypeUtils.getMethodName(obj, constantPool).equals(JVMConst.CONSTRUCTOR_NAME)) {
            final UninitializedObjectType t = (UninitializedObjectType) stack().peek(AdvancedTypeUtils.getArgumentTypes(obj, constantPool).length);
            if (t == Frame.getThis()) {
                Frame.setThis(null);
            }
            stack().initializeObject(t);
            locals().initializeObject(t);
        }
        stack().pop();    //objectref
        for (int i = 0; i < AdvancedTypeUtils.getArgumentTypes(obj, constantPool).length; i++) {
            stack().pop();
        }
        // We are sure the invoked d_method will xRETURN eventually
        // We simulate xRETURNs functionality here because we
        // don't really "jump into" and simulate the invoked
        // d_method.
        if (AdvancedTypeUtils.getReturnType(obj, constantPool) != TypeUtils.VOID) {
            Type t = AdvancedTypeUtils.getReturnType(obj, constantPool);
            if (t.equals(TypeUtils.BOOLEAN) ||
                    t.equals(TypeUtils.CHAR) ||
                    t.equals(TypeUtils.BYTE) ||
                    t.equals(TypeUtils.SHORT)) {
                t = TypeUtils.INT;
            }
            stack().push(t);
        }
    }

    @Override
    public void visitINVOKESTATIC(final INVOKESTATIC obj) {
        for (int i = 0; i < AdvancedTypeUtils.getArgumentTypes(obj, constantPool).length; i++) {
            stack().pop();
        }
        // We are sure the invoked d_method will xRETURN eventually
        // We simulate xRETURNs functionality here because we
        // don't really "jump into" and simulate the invoked
        // d_method.
        if (AdvancedTypeUtils.getReturnType(obj, constantPool) != TypeUtils.VOID) {
            Type t = AdvancedTypeUtils.getReturnType(obj, constantPool);
            if (t.equals(TypeUtils.BOOLEAN) ||
                    t.equals(TypeUtils.CHAR) ||
                    t.equals(TypeUtils.BYTE) ||
                    t.equals(TypeUtils.SHORT)) {
                t = TypeUtils.INT;
            }
            stack().push(t);
        }
    }

    @Override
    public void visitINVOKEVIRTUAL(final INVOKEVIRTUAL obj) {
        stack().pop(); //objectref
        for (int i = 0; i < AdvancedTypeUtils.getArgumentTypes(obj, constantPool).length; i++) {
            stack().pop();
        }
        // We are sure the invoked d_method will xRETURN eventually
        // We simulate xRETURNs functionality here because we
        // don't really "jump into" and simulate the invoked
        // d_method.
        if (AdvancedTypeUtils.getReturnType(obj, constantPool) != TypeUtils.VOID) {
            Type t = AdvancedTypeUtils.getReturnType(obj, constantPool);
            if (t.equals(TypeUtils.BOOLEAN) ||
                    t.equals(TypeUtils.CHAR) ||
                    t.equals(TypeUtils.BYTE) ||
                    t.equals(TypeUtils.SHORT)) {
                t = TypeUtils.INT;
            }
            stack().push(t);
        }
    }

    @Override
    public void visitIOR(final IOR obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitIREM(final IREM obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitIRETURN(final IRETURN obj) {
        stack().pop();
    }

    @Override
    public void visitISHL(final ISHL obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitISHR(final ISHR obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitISTORE(final ISTORE obj) {
        locals().set(obj.getIndex(), stack().pop());
    }

    @Override
    public void visitISUB(final ISUB obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitIUSHR(final IUSHR obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitIXOR(final IXOR obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitJSR(final JSR obj) {
        // FIXME: RuntimeException
        throw new RuntimeException("visitJSR");
        //f_stack().push(new ReturnaddressType(obj.physicalSuccessor()));
        //System.err.println("TODO-----------:"+o.physicalSuccessor());
    }

    @Override
    public void visitJSR_W(final JSR_W obj) {
        // FIXME: RuntimeException
        throw new RuntimeException("visitJSR_W");
        //f_stack().push(new ReturnaddressType(obj.physicalSuccessor()));
    }

    @Override
    public void visitL2D(final L2D obj) {
        stack().pop();
        stack().push(TypeUtils.DOUBLE);
    }

    @Override
    public void visitL2F(final L2F obj) {
        stack().pop();
        stack().push(TypeUtils.FLOAT);
    }

    @Override
    public void visitL2I(final L2I obj) {
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitLADD(final LADD obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLALOAD(final LALOAD obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLAND(final LAND obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLASTORE(final LASTORE obj) {
        stack().pop();
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitLCMP(final LCMP obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitLDC(final LDC obj) {
        final Constant c = constantPool.getConstant(obj.getIndex());
        if (c instanceof ConstantInteger) {
            stack().push(TypeUtils.INT);
        }
        if (c instanceof ConstantFloat) {
            stack().push(TypeUtils.FLOAT);
        }
        if (c instanceof ConstantString) {
            stack().push(TypeUtils.STRING);
        }
        if (c instanceof ConstantClass) {
            stack().push(TypeUtils.CLASS);
        }
    }

    public void visitLDC_W(final LDC_W obj) {
        final Constant c = constantPool.getConstant(obj.getIndex());
        if (c instanceof ConstantInteger) {
            stack().push(TypeUtils.INT);
        }
        if (c instanceof ConstantFloat) {
            stack().push(TypeUtils.FLOAT);
        }
        if (c instanceof ConstantString) {
            stack().push(TypeUtils.STRING);
        }
        if (c instanceof ConstantClass) {
            stack().push(TypeUtils.CLASS);
        }
    }

    @Override
    public void visitLDC2_W(final LDC2_W obj) {
        final Constant c = constantPool.getConstant(obj.getIndex());
        if (c instanceof ConstantLong) {
            stack().push(TypeUtils.LONG);
        }
        if (c instanceof ConstantDouble) {
            stack().push(TypeUtils.DOUBLE);
        }
    }

    @Override
    public void visitLDIV(final LDIV obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLLOAD(final LLOAD obj) {
        stack().push(locals().get(obj.getIndex()));
    }

    @Override
    public void visitLMUL(final LMUL obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLNEG(final LNEG obj) {
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLOOKUPSWITCH(final LOOKUPSWITCH obj) {
        stack().pop(); //key
    }

    @Override
    public void visitLOR(final LOR obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLREM(final LREM obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLRETURN(final LRETURN obj) {
        stack().pop();
    }

    @Override
    public void visitLSHL(final LSHL obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLSHR(final LSHR obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLSTORE(final LSTORE obj) {
        locals().set(obj.getIndex(), stack().pop());
        locals().set(obj.getIndex()+1, TypeUtils.UNKNOWN);
    }

    @Override
    public void visitLSUB(final LSUB obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLUSHR(final LUSHR obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitLXOR(final LXOR obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.LONG);
    }

    @Override
    public void visitMONITORENTER(final MONITORENTER obj) {
        stack().pop();
    }

    @Override
    public void visitMONITOREXIT(final MONITOREXIT obj) {
        stack().pop();
    }

    @Override
    public void visitWIDE(WIDE obj) {

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
        for (int i=0; i<obj.dimensions; i++) {
            stack().pop();
        }
        Type type = AdvancedTypeUtils.getMULTIANEWARRAY(obj, constantPool);
        stack().push(type);
    }

    @Override
    public void visitNEW(final NEW obj) {
        Type type = AdvancedTypeUtils.getNEW(obj, constantPool);
        stack().push(new UninitializedObjectType((ObjectType) (type)));
    }

    @Override
    public void visitNEWARRAY(final NEWARRAY obj) {
        stack().pop();
        stack().push(TypeUtils.getType(obj.atype));
    }

    @Override
    public void visitNOP(final NOP obj) {
    }

    @Override
    public void visitPOP(final POP obj) {
        stack().pop();
    }

    @Override
    public void visitPOP2(final POP2 obj) {
        final Type t = stack().pop();
        if (t.getSize() == 1) {
            stack().pop();
        }
    }

    @Override
    public void visitPUTFIELD(final PUTFIELD obj) {
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitPUTSTATIC(final PUTSTATIC obj) {
        stack().pop();
    }

    @Override
    public void visitRET(final RET obj) {
        // do nothing, return address
        // is in in the local variables.
    }

    @Override
    public void visitRETURN(final RETURN obj) {
        // do nothing.
    }

    @Override
    public void visitSALOAD(final SALOAD obj) {
        stack().pop();
        stack().pop();
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitSASTORE(final SASTORE obj) {
        stack().pop();
        stack().pop();
        stack().pop();
    }

    @Override
    public void visitSIPUSH(final SIPUSH obj) {
        stack().push(TypeUtils.INT);
    }

    @Override
    public void visitSWAP(final SWAP obj) {
        final Type t = stack().pop();
        final Type u = stack().pop();
        stack().push(t);
        stack().push(u);
    }

    @Override
    public void visitTABLESWITCH(final TABLESWITCH obj) {
        stack().pop();
    }
}
