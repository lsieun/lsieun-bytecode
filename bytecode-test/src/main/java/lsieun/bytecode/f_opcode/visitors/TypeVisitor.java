package lsieun.bytecode.f_opcode.visitors;

import lsieun.bytecode.cp.Constant;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.core.type.ArrayType;
import lsieun.bytecode.core.type.ObjectType;
import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.opcode.*;
import lsieun.bytecode.opcode.type.ReturnaddressType;

@SuppressWarnings("Duplicates")
public class TypeVisitor implements OpcodeVisitor {
    private Type type;
    private ConstantPool constant_pool;

    public Type getType(Instruction instruction) {
        instruction.accept(this);
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void visitNOP(NOP obj) {

    }

    @Override
    public void visitACONST_NULL(ACONST_NULL obj) {
        type = TypeUtils.NULL;
    }

    @Override
    public void visitICONST_M1(ICONST_M1 obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitICONST_0(ICONST_0 obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitICONST_1(ICONST_1 obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitICONST_2(ICONST_2 obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitICONST_3(ICONST_3 obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitICONST_4(ICONST_4 obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitICONST_5(ICONST_5 obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLCONST_0(LCONST_0 obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitLCONST_1(LCONST_1 obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitFCONST_0(FCONST_0 obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitFCONST_1(FCONST_1 obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitFCONST_2(FCONST_2 obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitDCONST_0(DCONST_0 obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDCONST_1(DCONST_1 obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitBIPUSH(BIPUSH obj) {
        setType(TypeUtils.BYTE);
    }

    @Override
    public void visitSIPUSH(SIPUSH obj) {
        setType(TypeUtils.SHORT);
    }

    @Override
    public void visitLDC(LDC obj) {
        Constant constant = constant_pool.getConstant(obj.index);
        switch (constant.tag) {
            case CPConst.CONSTANT_String:
                setType(TypeUtils.STRING);
                break;
            case CPConst.CONSTANT_Float:
                setType(TypeUtils.FLOAT);
                break;
            case CPConst.CONSTANT_Integer:
                setType(TypeUtils.INT);
                break;
            case CPConst.CONSTANT_Class:
                setType(TypeUtils.CLASS);
                break;
            default: // Never reached
                throw new RuntimeException("Unknown or invalid constant type at " + obj.index);
        }
    }

    @Override
    public void visitLDC_W(LDC_W obj) {
        Constant constant = constant_pool.getConstant(obj.index);
        switch (constant.tag) {
            case CPConst.CONSTANT_String:
                setType(TypeUtils.STRING);
                break;
            case CPConst.CONSTANT_Float:
                setType(TypeUtils.FLOAT);
                break;
            case CPConst.CONSTANT_Integer:
                setType(TypeUtils.INT);
                break;
            case CPConst.CONSTANT_Class:
                setType(TypeUtils.CLASS);
                break;
            default: // Never reached
                throw new RuntimeException("Unknown or invalid constant type at " + obj.index);
        }
    }

    @Override
    public void visitLDC2_W(LDC2_W obj) {
        Constant constant = constant_pool.getConstant(obj.index);
        switch (constant.tag) {
            case CPConst.CONSTANT_Long:
                setType(TypeUtils.LONG);
                break;
            case CPConst.CONSTANT_Double:
                setType(TypeUtils.DOUBLE);
                break;
            default: // Never reached
                throw new RuntimeException("Unknown constant type at " + obj.index);
        }
    }

    @Override
    public void visitILOAD(ILOAD obj) {
        type = TypeUtils.INT;
    }

    @Override
    public void visitLLOAD(LLOAD obj) {
        type = TypeUtils.LONG;
    }

    @Override
    public void visitFLOAD(FLOAD obj) {
        type = TypeUtils.FLOAT;
    }

    @Override
    public void visitDLOAD(DLOAD obj) {
        type = TypeUtils.DOUBLE;
    }

    @Override
    public void visitALOAD(ALOAD obj) {
        type = TypeUtils.OBJECT;
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
    public void visitIALOAD(IALOAD obj) {
        type = TypeUtils.INT;
    }

    @Override
    public void visitLALOAD(LALOAD obj) {
        type = TypeUtils.LONG;
    }

    @Override
    public void visitFALOAD(FALOAD obj) {
        type = TypeUtils.FLOAT;
    }

    @Override
    public void visitDALOAD(DALOAD obj) {
        type = TypeUtils.DOUBLE;
    }

    @Override
    public void visitAALOAD(AALOAD obj) {
        type = TypeUtils.OBJECT;
    }

    @Override
    public void visitBALOAD(BALOAD obj) {
        type = TypeUtils.BYTE;
    }

    @Override
    public void visitCALOAD(CALOAD obj) {
        type = TypeUtils.CHAR;
    }

    @Override
    public void visitSALOAD(SALOAD obj) {
        type = TypeUtils.SHORT;
    }

    @Override
    public void visitISTORE(ISTORE obj) {
        type = TypeUtils.INT;
    }

    @Override
    public void visitLSTORE(LSTORE obj) {
        type = TypeUtils.LONG;
    }

    @Override
    public void visitFSTORE(FSTORE obj) {
        type = TypeUtils.FLOAT;
    }

    @Override
    public void visitDSTORE(DSTORE obj) {
        type = TypeUtils.DOUBLE;
    }

    @Override
    public void visitASTORE(ASTORE obj) {
        type = TypeUtils.OBJECT;
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
    public void visitIASTORE(IASTORE obj) {
        type = TypeUtils.INT;
    }

    @Override
    public void visitLASTORE(LASTORE obj) {
        type = TypeUtils.LONG;
    }

    @Override
    public void visitFASTORE(FASTORE obj) {
        type = TypeUtils.FLOAT;
    }

    @Override
    public void visitDASTORE(DASTORE obj) {
        type = TypeUtils.DOUBLE;
    }

    @Override
    public void visitAASTORE(AASTORE obj) {
        type = TypeUtils.OBJECT;
    }

    @Override
    public void visitBASTORE(BASTORE obj) {
        type = TypeUtils.BYTE;
    }

    @Override
    public void visitCASTORE(CASTORE obj) {
        type = TypeUtils.CHAR;
    }

    @Override
    public void visitSASTORE(SASTORE obj) {

    }

    @Override
    public void visitPOP(POP obj) {
        setType(TypeUtils.UNKNOWN);
    }

    @Override
    public void visitPOP2(POP2 obj) {
        setType(TypeUtils.UNKNOWN);
    }

    @Override
    public void visitDUP(DUP obj) {
        setType(TypeUtils.UNKNOWN);
    }

    @Override
    public void visitDUP_X1(DUP_X1 obj) {
        setType(TypeUtils.UNKNOWN);
    }

    @Override
    public void visitDUP_X2(DUP_X2 obj) {
        setType(TypeUtils.UNKNOWN);
    }

    @Override
    public void visitDUP2(DUP2 obj) {
        setType(TypeUtils.UNKNOWN);
    }

    @Override
    public void visitDUP2_X1(DUP2_X1 obj) {
        setType(TypeUtils.UNKNOWN);
    }

    @Override
    public void visitDUP2_X2(DUP2_X2 obj) {
        setType(TypeUtils.UNKNOWN);
    }

    @Override
    public void visitSWAP(SWAP obj) {
        setType(TypeUtils.UNKNOWN);
    }

    @Override
    public void visitIADD(IADD obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLADD(LADD obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitFADD(FADD obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitDADD(DADD obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitISUB(ISUB obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLSUB(LSUB obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitFSUB(FSUB obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitDSUB(DSUB obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitIMUL(IMUL obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLMUL(LMUL obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitFMUL(FMUL obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitDMUL(DMUL obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitIDIV(IDIV obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLDIV(LDIV obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitFDIV(FDIV obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitDDIV(DDIV obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitIREM(IREM obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLREM(LREM obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitFREM(FREM obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitDREM(DREM obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitINEG(INEG obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLNEG(LNEG obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitFNEG(FNEG obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitDNEG(DNEG obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitISHL(ISHL obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLSHL(LSHL obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitISHR(ISHR obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLSHR(LSHR obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitIUSHR(IUSHR obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLUSHR(LUSHR obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitIAND(IAND obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLAND(LAND obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitIOR(IOR obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLOR(LOR obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitIXOR(IXOR obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLXOR(LXOR obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitIINC(IINC obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitI2L(I2L obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitI2F(I2F obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitI2D(I2D obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitL2I(L2I obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitL2F(L2F obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitL2D(L2D obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitF2I(F2I obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitF2L(F2L obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitF2D(F2D obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitD2I(D2I obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitD2L(D2L obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitD2F(D2F obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitI2B(I2B obj) {
        setType(TypeUtils.BYTE);
    }

    @Override
    public void visitI2C(I2C obj) {
        setType(TypeUtils.CHAR);
    }

    @Override
    public void visitI2S(I2S obj) {
        setType(TypeUtils.SHORT);
    }

    @Override
    public void visitLCMP(LCMP obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitFCMPL(FCMPL obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitFCMPG(FCMPG obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitDCMPL(DCMPL obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitDCMPG(DCMPG obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitIFEQ(IFEQ obj) {

    }

    @Override
    public void visitIFNE(IFNE obj) {

    }

    @Override
    public void visitIFLT(IFLT obj) {

    }

    @Override
    public void visitIFGE(IFGE obj) {

    }

    @Override
    public void visitIFGT(IFGT obj) {

    }

    @Override
    public void visitIFLE(IFLE obj) {

    }

    @Override
    public void visitIF_ICMPEQ(IF_ICMPEQ obj) {

    }

    @Override
    public void visitIF_ICMPNE(IF_ICMPNE obj) {

    }

    @Override
    public void visitIF_ICMPLT(IF_ICMPLT obj) {

    }

    @Override
    public void visitIF_ICMPGE(IF_ICMPGE obj) {

    }

    @Override
    public void visitIF_ICMPGT(IF_ICMPGT obj) {

    }

    @Override
    public void visitIF_ICMPLE(IF_ICMPLE obj) {

    }

    @Override
    public void visitIF_ACMPEQ(IF_ACMPEQ obj) {

    }

    @Override
    public void visitIF_ACMPNE(IF_ACMPNE obj) {

    }

    @Override
    public void visitIFNULL(IFNULL obj) {

    }

    @Override
    public void visitIFNONNULL(IFNONNULL obj) {

    }

    @Override
    public void visitGOTO(GOTO obj) {

    }

    @Override
    public void visitGOTO_W(GOTO_W obj) {

    }

    @Override
    public void visitJSR(JSR obj) {

    }

    @Override
    public void visitJSR_W(JSR_W obj) {

    }

    @Override
    public void visitRET(RET obj) {
        setType(ReturnaddressType.NO_TARGET);
    }

    @Override
    public void visitTABLESWITCH(TABLESWITCH obj) {

    }

    @Override
    public void visitLOOKUPSWITCH(LOOKUPSWITCH obj) {

    }

    @Override
    public void visitIRETURN(IRETURN obj) {
        setType(TypeUtils.INT);
    }

    @Override
    public void visitLRETURN(LRETURN obj) {
        setType(TypeUtils.LONG);
    }

    @Override
    public void visitFRETURN(FRETURN obj) {
        setType(TypeUtils.FLOAT);
    }

    @Override
    public void visitDRETURN(DRETURN obj) {
        setType(TypeUtils.DOUBLE);
    }

    @Override
    public void visitARETURN(ARETURN obj) {
        setType(TypeUtils.OBJECT);
    }

    @Override
    public void visitRETURN(RETURN obj) {
        setType(TypeUtils.VOID);
    }

    @Override
    public void visitGETSTATIC(GETSTATIC obj) {

    }

    @Override
    public void visitPUTSTATIC(PUTSTATIC obj) {

    }

    @Override
    public void visitGETFIELD(GETFIELD obj) {

    }

    @Override
    public void visitPUTFIELD(PUTFIELD obj) {

    }

    @Override
    public void visitINVOKEVIRTUAL(INVOKEVIRTUAL obj) {

    }

    @Override
    public void visitINVOKESPECIAL(INVOKESPECIAL obj) {

    }

    @Override
    public void visitINVOKESTATIC(INVOKESTATIC obj) {

    }

    @Override
    public void visitINVOKEINTERFACE(INVOKEINTERFACE obj) {

    }

    @Override
    public void visitINVOKEDYNAMIC(INVOKEDYNAMIC obj) {
        setType(new ObjectType(Object.class.getName()));
    }

    @Override
    public void visitNEW(NEW obj) {
        Constant c = constant_pool.getConstant(obj.index);
        String signature = c.value;
        Type t = TypeUtils.getType(signature);
        setType(t);
    }

    @Override
    public void visitNEWARRAY(NEWARRAY obj) {
        Type t = TypeUtils.getType(obj.atype);
        setType(new ArrayType(t, 1));
    }

    @Override
    public void visitANEWARRAY(ANEWARRAY obj) {

    }

    @Override
    public void visitARRAYLENGTH(ARRAYLENGTH obj) {

    }

    @Override
    public void visitMULTIANEWARRAY(MULTIANEWARRAY obj) {
        Constant c = constant_pool.getConstant(obj.index);
        String signature = c.value;
        Type t = TypeUtils.getType(signature);

        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        setType((t instanceof ObjectType) ? (ObjectType) t : null);
    }

    @Override
    public void visitATHROW(ATHROW obj) {

    }

    @Override
    public void visitCHECKCAST(CHECKCAST obj) {

    }

    @Override
    public void visitINSTANCEOF(INSTANCEOF obj) {

    }

    @Override
    public void visitMONITORENTER(MONITORENTER obj) {

    }

    @Override
    public void visitMONITOREXIT(MONITOREXIT obj) {

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
}
