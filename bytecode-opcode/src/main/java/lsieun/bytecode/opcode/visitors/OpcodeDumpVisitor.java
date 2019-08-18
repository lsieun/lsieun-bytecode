package lsieun.bytecode.opcode.visitors;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.opcode.*;
import lsieun.bytecode.opcode.utils.InstructionChain;

@SuppressWarnings("Duplicates")
public class OpcodeDumpVisitor implements OpcodeVisitor {

    // region static member
    private static final ThreadLocal<Boolean> wide_state =
            new ThreadLocal<Boolean>() {
                @Override
                protected Boolean initialValue() {
                    return false;
                }
            };

    public static boolean wide() {
        return wide_state.get();
    }

    public static void wide(boolean flag) {
        wide_state.set(flag);
    }
    // endregion

    private final ByteArrayOutputStream bao;
    private final DataOutputStream out;

    public OpcodeDumpVisitor() {
        bao = new ByteArrayOutputStream();
        out = new DataOutputStream(bao);
    }

    public byte[] getByteCode() {
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bao.toByteArray();
    }

    public void visitInstructionList(InstructionChain il) {
        Instruction current = il.start;
        while (current != null) {
            if(current instanceof WIDE) {
                wide(true);
            }
            else {
                wide(false);
            }

            current.accept(this);

            current = current.next;
        }

    }

    @Override
    public void visitNOP(NOP obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitACONST_NULL(ACONST_NULL obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitICONST_M1(ICONST_M1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitICONST_0(ICONST_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitICONST_1(ICONST_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitICONST_2(ICONST_2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitICONST_3(ICONST_3 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitICONST_4(ICONST_4 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitICONST_5(ICONST_5 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLCONST_0(LCONST_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLCONST_1(LCONST_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFCONST_0(FCONST_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFCONST_1(FCONST_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFCONST_2(FCONST_2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDCONST_0(DCONST_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDCONST_1(DCONST_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitBIPUSH(BIPUSH obj) {
        try {
            out.writeByte(obj.opcode);
            out.writeByte(obj.value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visitSIPUSH(SIPUSH obj) {
        writeByte(obj.opcode);
        writeShort(obj.value);
    }

    @Override
    public void visitLDC(LDC obj) {
        writeByte(obj.opcode);
        writeByte(obj.index);
    }

    @Override
    public void visitLDC_W(LDC_W obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitLDC2_W(LDC2_W obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitILOAD(ILOAD obj) {
        boolean wide = wide();
        writeByte(obj.opcode);
        if (wide) {
            writeShort(obj.index);
        } else {
            writeByte(obj.index);
        }
    }

    @Override
    public void visitLLOAD(LLOAD obj) {
        boolean wide = wide();
        writeByte(obj.opcode);
        if (wide) {
            writeShort(obj.index);
        } else {
            writeByte(obj.index);
        }
    }

    @Override
    public void visitFLOAD(FLOAD obj) {
        boolean wide = wide();
        writeByte(obj.opcode);
        if (wide) {
            writeShort(obj.index);
        } else {
            writeByte(obj.index);
        }
    }

    @Override
    public void visitDLOAD(DLOAD obj) {
        boolean wide = wide();
        writeByte(obj.opcode);
        if (wide) {
            writeShort(obj.index);
        } else {
            writeByte(obj.index);
        }
    }

    @Override
    public void visitALOAD(ALOAD obj) {
        boolean wide = wide();
        writeByte(obj.opcode);
        if (wide) {
            writeShort(obj.index);
        } else {
            writeByte(obj.index);
        }
    }

    @Override
    public void visitILOAD_0(ILOAD_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitILOAD_1(ILOAD_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitILOAD_2(ILOAD_2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitILOAD_3(ILOAD_3 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLLOAD_0(LLOAD_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLLOAD_1(LLOAD_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLLOAD_2(LLOAD_2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLLOAD_3(LLOAD_3 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFLOAD_0(FLOAD_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFLOAD_1(FLOAD_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFLOAD_2(FLOAD_2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFLOAD_3(FLOAD_3 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDLOAD_0(DLOAD_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDLOAD_1(DLOAD_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDLOAD_2(DLOAD_2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDLOAD_3(DLOAD_3 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitALOAD_0(ALOAD_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitALOAD_1(ALOAD_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitALOAD_2(ALOAD_2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitALOAD_3(ALOAD_3 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitIALOAD(IALOAD obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLALOAD(LALOAD obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFALOAD(FALOAD obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDALOAD(DALOAD obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitAALOAD(AALOAD obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitBALOAD(BALOAD obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitCALOAD(CALOAD obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitSALOAD(SALOAD obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitISTORE(ISTORE obj) {
        boolean wide = wide();
        writeByte(obj.opcode);
        if (wide) {
            writeShort(obj.index);
        } else {
            writeByte(obj.index);
        }
    }

    @Override
    public void visitLSTORE(LSTORE obj) {
        boolean wide = wide();
        writeByte(obj.opcode);
        if (wide) {
            writeShort(obj.index);
        } else {
            writeByte(obj.index);
        }
    }

    @Override
    public void visitFSTORE(FSTORE obj) {
        boolean wide = wide();
        writeByte(obj.opcode);
        if (wide) {
            writeShort(obj.index);
        } else {
            writeByte(obj.index);
        }
    }

    @Override
    public void visitDSTORE(DSTORE obj) {
        boolean wide = wide();
        writeByte(obj.opcode);
        if (wide) {
            writeShort(obj.index);
        } else {
            writeByte(obj.index);
        }
    }

    @Override
    public void visitASTORE(ASTORE obj) {
        boolean wide = wide();
        writeByte(obj.opcode);
        if (wide) {
            writeShort(obj.index);
        } else {
            writeByte(obj.index);
        }
    }

    @Override
    public void visitISTORE_0(ISTORE_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitISTORE_1(ISTORE_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitISTORE_2(ISTORE_2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitISTORE_3(ISTORE_3 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLSTORE_0(LSTORE_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLSTORE_1(LSTORE_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLSTORE_2(LSTORE_2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLSTORE_3(LSTORE_3 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFSTORE_0(FSTORE_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFSTORE_1(FSTORE_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFSTORE_2(FSTORE_2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFSTORE_3(FSTORE_3 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDSTORE_0(DSTORE_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDSTORE_1(DSTORE_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDSTORE_2(DSTORE_2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDSTORE_3(DSTORE_3 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitASTORE_0(ASTORE_0 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitASTORE_1(ASTORE_1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitASTORE_2(ASTORE_2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitASTORE_3(ASTORE_3 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitIASTORE(IASTORE obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLASTORE(LASTORE obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFASTORE(FASTORE obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDASTORE(DASTORE obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitAASTORE(AASTORE obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitBASTORE(BASTORE obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitCASTORE(CASTORE obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitSASTORE(SASTORE obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitPOP(POP obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitPOP2(POP2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDUP(DUP obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDUP_X1(DUP_X1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDUP_X2(DUP_X2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDUP2(DUP2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDUP2_X1(DUP2_X1 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDUP2_X2(DUP2_X2 obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitSWAP(SWAP obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitIADD(IADD obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLADD(LADD obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFADD(FADD obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDADD(DADD obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitISUB(ISUB obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLSUB(LSUB obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFSUB(FSUB obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDSUB(DSUB obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitIMUL(IMUL obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLMUL(LMUL obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFMUL(FMUL obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDMUL(DMUL obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitIDIV(IDIV obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLDIV(LDIV obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFDIV(FDIV obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDDIV(DDIV obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitIREM(IREM obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLREM(LREM obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFREM(FREM obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDREM(DREM obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitINEG(INEG obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLNEG(LNEG obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFNEG(FNEG obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDNEG(DNEG obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitISHL(ISHL obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLSHL(LSHL obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitISHR(ISHR obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLSHR(LSHR obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitIUSHR(IUSHR obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLUSHR(LUSHR obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitIAND(IAND obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLAND(LAND obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitIOR(IOR obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLOR(LOR obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitIXOR(IXOR obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLXOR(LXOR obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitIINC(IINC obj) {
        boolean wide = wide();

        if (!wide) {
            writeByte(obj.opcode);
            writeByte(obj.index);
            writeByte(obj.constValue);
        } else {
            writeByte(OpcodeConst.WIDE);
            writeByte(obj.opcode);
            writeShort(obj.index);
            writeShort(obj.constValue);
        }
    }

    @Override
    public void visitI2L(I2L obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitI2F(I2F obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitI2D(I2D obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitL2I(L2I obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitL2F(L2F obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitL2D(L2D obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitF2I(F2I obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitF2L(F2L obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitF2D(F2D obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitD2I(D2I obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitD2L(D2L obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitD2F(D2F obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitI2B(I2B obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitI2C(I2C obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitI2S(I2S obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLCMP(LCMP obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFCMPL(FCMPL obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFCMPG(FCMPG obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDCMPL(DCMPL obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDCMPG(DCMPG obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitIFEQ(IFEQ obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIFNE(IFNE obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIFLT(IFLT obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIFGE(IFGE obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIFGT(IFGT obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIFLE(IFLE obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIF_ICMPEQ(IF_ICMPEQ obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIF_ICMPNE(IF_ICMPNE obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIF_ICMPLT(IF_ICMPLT obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIF_ICMPGE(IF_ICMPGE obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIF_ICMPGT(IF_ICMPGT obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIF_ICMPLE(IF_ICMPLE obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIF_ACMPEQ(IF_ACMPEQ obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIF_ACMPNE(IF_ACMPNE obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitGOTO(GOTO obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitJSR(JSR obj) {
        throw new RuntimeException("visitJSR");
    }

    @Override
    public void visitRET(RET obj) {
        throw new RuntimeException("visitRET");
    }

    @Override
    public void visitTABLESWITCH(TABLESWITCH obj) {
        int padding = obj.padding = (4 - ((obj.pos+1) % 4)) % 4;
        int match_length = obj.high - obj.low + 1;
        int[] matchs = obj.matches;
        int[] offsets = obj.offsets;

        writeByte(obj.opcode);
        for (int i = 0; i < padding; i++) {
            writeByte(0);
        }
        writeInt(obj.default_branch);

        final int low = (match_length > 0) ? matchs[0] : 0;
        writeInt(low);
        final int high = (match_length > 0) ? matchs[match_length - 1] : 0;
        writeInt(high);
        for (int i = 0; i < match_length; i++) {
            writeInt(offsets[i]);
        }
    }

    @Override
    public void visitLOOKUPSWITCH(LOOKUPSWITCH obj) {
        int padding = obj.padding = (4 - ((obj.pos+1) % 4)) % 4;
        int match_length = obj.npairs;
        int[] matches = obj.matches;
        int[] offsets = obj.offsets;

        writeByte(obj.opcode);
        for (int i = 0; i < padding; i++) {
            writeByte(0);
        }
        writeInt(obj.default_branch);

        writeInt(match_length); // npairs
        for (int i = 0; i < match_length; i++) {
            writeInt(matches[i]); // match-offset pairs
            writeInt(offsets[i]);
        }
    }

    @Override
    public void visitIRETURN(IRETURN obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitLRETURN(LRETURN obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitFRETURN(FRETURN obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitDRETURN(DRETURN obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitARETURN(ARETURN obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitRETURN(RETURN obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitGETSTATIC(GETSTATIC obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitPUTSTATIC(PUTSTATIC obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitGETFIELD(GETFIELD obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitPUTFIELD(PUTFIELD obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitINVOKEVIRTUAL(INVOKEVIRTUAL obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitINVOKESPECIAL(INVOKESPECIAL obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitINVOKESTATIC(INVOKESTATIC obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitINVOKEINTERFACE(INVOKEINTERFACE obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
        writeByte(obj.count);
        writeByte(0);
    }

    @Override
    public void visitINVOKEDYNAMIC(INVOKEDYNAMIC obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
        writeByte(0);
        writeByte(0);
    }

    @Override
    public void visitNEW(NEW obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitNEWARRAY(NEWARRAY obj) {
        writeByte(obj.opcode);
        writeByte(obj.atype);
    }

    @Override
    public void visitANEWARRAY(ANEWARRAY obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitARRAYLENGTH(ARRAYLENGTH obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitATHROW(ATHROW obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitCHECKCAST(CHECKCAST obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitINSTANCEOF(INSTANCEOF obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
    }

    @Override
    public void visitMONITORENTER(MONITORENTER obj) {
        throw new RuntimeException("visitMONITORENTER");
    }

    @Override
    public void visitMONITOREXIT(MONITOREXIT obj) {
        throw new RuntimeException("visitMONITOREXIT");
    }

    @Override
    public void visitWIDE(WIDE obj) {
        writeByte(obj.opcode);
    }

    @Override
    public void visitBREAKPOINT(BREAKPOINT obj) {
        throw new RuntimeException("visitBREAKPOINT");
    }

    @Override
    public void visitIMPDEP1(IMPDEP1 obj) {
        throw new RuntimeException("visitIMPDEP1");
    }

    @Override
    public void visitIMPDEP2(IMPDEP2 obj) {
        throw new RuntimeException("visitIMPDEP2");
    }

    @Override
    public void visitMULTIANEWARRAY(MULTIANEWARRAY obj) {
        writeByte(obj.opcode);
        writeShort(obj.index);
        writeByte(obj.dimensions);
    }

    @Override
    public void visitIFNULL(IFNULL obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitIFNONNULL(IFNONNULL obj) {
        writeByte(obj.opcode);
        writeShort(obj.branch);
    }

    @Override
    public void visitGOTO_W(GOTO_W obj) {
        writeByte(obj.opcode);
        writeInt(obj.branch);
    }

    @Override
    public void visitJSR_W(JSR_W obj) {
        throw new RuntimeException("visitJSR_W");
    }

    private void writeByte(int value) {
        try {
            out.writeByte(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeShort(int value) {
        try {
            out.writeShort(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInt(int value) {
        try {
            out.writeInt(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
