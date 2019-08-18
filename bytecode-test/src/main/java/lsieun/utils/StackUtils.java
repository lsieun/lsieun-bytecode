package lsieun.utils;

import lsieun.bytecode.cp.ConstantNameAndType;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.cp.ConstantUtf8;
import lsieun.bytecode.cp.facet.ConstantRef;
import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.facet.CPInstruction;
import lsieun.bytecode.opcode.facet.FieldInstruction;
import lsieun.bytecode.opcode.facet.InvokeInstruction;
import lsieun.bytecode.opcode.opcode.*;

public class StackUtils {
    public static int consumeStack(final Instruction ins, final ConstantPool constant_pool) {
        int opcode = ins.opcode;

        if (ins instanceof INVOKEINTERFACE) {
            INVOKEINTERFACE ii = (INVOKEINTERFACE) ins;
            return ii.count;
        } else if (ins instanceof InvokeInstruction) {
            InvokeInstruction ii = (InvokeInstruction) ins;
            int sum;
            if ((opcode == OpcodeConst.INVOKESTATIC) || (opcode == OpcodeConst.INVOKEDYNAMIC)) {
                sum = 0;
            } else {
                sum = 1; // this reference
            }

            final String signature = getSignature(ii, constant_pool);
            sum += TypeUtils.getArgumentTypesSize(signature);
            return sum;
        } else if (ins instanceof PUTFIELD) {
            PUTFIELD putfield = (PUTFIELD) ins;
            return getFieldSize(putfield, constant_pool) + 1;
        } else if (ins instanceof PUTSTATIC) {
            PUTSTATIC putstatic = (PUTSTATIC) ins;
            return getFieldSize(putstatic, constant_pool);
        }

        return OpcodeConst.getConsumeStack(opcode);
    }


    public static int getFieldSize(FieldInstruction fieldInstruction, final ConstantPool constant_pool) {
        String signature = getSignature(fieldInstruction, constant_pool);
        return TypeUtils.size(TypeUtils.getTypeSizeAndCharNum(signature));
    }

    public static String getSignature(CPInstruction ins, final ConstantPool constant_pool) {
        final ConstantRef c = (ConstantRef) constant_pool.getConstant(ins.getIndex());
        final ConstantNameAndType cnat = (ConstantNameAndType) constant_pool.getConstant(c.getNameAndTypeIndex());
        return ((ConstantUtf8) constant_pool.getConstant(cnat.descriptor_index)).value;
    }

    public static int produceStack(final Instruction ins, final ConstantPool constant_pool) {
        int opcode = ins.opcode;

        if (ins instanceof InvokeInstruction) {
            InvokeInstruction ii = (InvokeInstruction) ins;
            final String signature = getSignature(ii, constant_pool);
            return TypeUtils.getReturnTypeSize(signature);
        } else if (ins instanceof GETFIELD) {
            GETFIELD getfield = (GETFIELD) ins;
            return getFieldSize(getfield, constant_pool);
        } else if (ins instanceof GETSTATIC) {
            GETSTATIC getstatic = (GETSTATIC) ins;
            return getFieldSize(getstatic, constant_pool);
        }

        return OpcodeConst.getProduceStack(opcode);
    }
}
