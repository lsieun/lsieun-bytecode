package lsieun.bytecode.utils;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.cp.*;
import lsieun.bytecode.cp.facet.ConstantRef;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.facet.FieldInstruction;
import lsieun.bytecode.opcode.facet.InvokeInstruction;
import lsieun.bytecode.opcode.facet.LocalVariableInstruction;
import lsieun.bytecode.opcode.opcode.*;

public class AdvancedTypeUtils {

    // region Class
    public static Type getNEW(final NEW obj, ConstantPool constant_pool) {
        int cpIndex = obj.getIndex();
        return getConstantClass(cpIndex, constant_pool);
    }

    public static Type getANEWARRAY(final ANEWARRAY obj, ConstantPool constant_pool) {
        int cpIndex = obj.getIndex();
        return getConstantClass(cpIndex, constant_pool);
    }

    public static Type getCHECKCAST(final CHECKCAST obj, ConstantPool constant_pool) {
        int cpIndex = obj.getIndex();
        return getConstantClass(cpIndex, constant_pool);
    }

    public static Type getMULTIANEWARRAY(final MULTIANEWARRAY obj, ConstantPool constant_pool) {
        int cpIndex = obj.getIndex();
        return getConstantClass(cpIndex, constant_pool);
    }

    private static Type getConstantClass(int cpIndex, ConstantPool constant_pool) {
        ConstantClass c = (ConstantClass) constant_pool.getConstant(cpIndex, CPConst.CONSTANT_Class);
        String name = constant_pool.getConstantString(c.name_index, CPConst.CONSTANT_Utf8);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return TypeUtils.getType(name);
    }
    // endregion

    // region Field
    public static Type getGETFIELD(final GETFIELD obj, ConstantPool constant_pool) {
        return getFieldInstruction(obj, constant_pool);
    }

    public static Type getGETSTATIC(final GETSTATIC obj, ConstantPool constant_pool) {
        return getFieldInstruction(obj, constant_pool);
    }

    private static Type getFieldInstruction(FieldInstruction obj, ConstantPool constant_pool) {
        int index = obj.getIndex();
        ConstantRef constantRef = (ConstantRef)constant_pool.getConstant(index, CPConst.CONSTANT_Fieldref);
        int nameAndTypeIndex = constantRef.getNameAndTypeIndex();
        ConstantNameAndType constantNameAndType = (ConstantNameAndType) constant_pool.getConstant(nameAndTypeIndex, CPConst.CONSTANT_NameAndType);
        int descriptorIndex = constantNameAndType.descriptor_index;
        String value = constant_pool.getConstantString(descriptorIndex, CPConst.CONSTANT_Utf8);
        return TypeUtils.getType(value);
    }
    // endregion

    // region Method
    public static Type[] getArgumentTypes(InvokeInstruction obj, ConstantPool constant_pool) {
        ConstantNameAndType constantNameAndType = getNameAndType(obj, constant_pool);
        int descriptorIndex = constantNameAndType.descriptor_index;
        String value = constant_pool.getConstantString(descriptorIndex, CPConst.CONSTANT_Utf8);
        return TypeUtils.getArgumentTypes(value);
    }

    public static Type getReturnType(InvokeInstruction obj, ConstantPool constant_pool) {
        ConstantNameAndType constantNameAndType = getNameAndType(obj, constant_pool);
        int descriptorIndex = constantNameAndType.descriptor_index;
        String value = constant_pool.getConstantString(descriptorIndex, CPConst.CONSTANT_Utf8);
        return TypeUtils.getReturnType(value);
    }

    public static String getMethodName(InvokeInstruction obj, ConstantPool constant_pool) {
        ConstantNameAndType constantNameAndType = getNameAndType(obj, constant_pool);
        int nameIndex = constantNameAndType.name_index;
        String value = constant_pool.getConstantString(nameIndex, CPConst.CONSTANT_Utf8);
        return value;
    }

    private static ConstantNameAndType getNameAndType(InvokeInstruction obj, ConstantPool constant_pool) {
        int cpIndex = obj.getIndex();
        ConstantRef constantRef = (ConstantRef)constant_pool.getConstant(cpIndex);
        int nameAndTypeIndex = constantRef.getNameAndTypeIndex();
        ConstantNameAndType constant = (ConstantNameAndType) constant_pool.getConstant(nameAndTypeIndex, CPConst.CONSTANT_NameAndType);
        return constant;
    }
    // endregion

    // region Code
    public static Type getType(LocalVariableInstruction obj) {
        Instruction ins = (Instruction) obj;
        int opcode = ins.opcode;
        switch (opcode) {
            case OpcodeConst.ILOAD:
            case OpcodeConst.ILOAD_0:
            case OpcodeConst.ILOAD_1:
            case OpcodeConst.ILOAD_2:
            case OpcodeConst.ILOAD_3:
            case OpcodeConst.ISTORE:
            case OpcodeConst.ISTORE_0:
            case OpcodeConst.ISTORE_1:
            case OpcodeConst.ISTORE_2:
            case OpcodeConst.ISTORE_3:
            case OpcodeConst.IINC:
                return TypeUtils.INT;
            case OpcodeConst.LLOAD:
            case OpcodeConst.LLOAD_0:
            case OpcodeConst.LLOAD_1:
            case OpcodeConst.LLOAD_2:
            case OpcodeConst.LLOAD_3:
            case OpcodeConst.LSTORE:
            case OpcodeConst.LSTORE_0:
            case OpcodeConst.LSTORE_1:
            case OpcodeConst.LSTORE_2:
            case OpcodeConst.LSTORE_3:
                return TypeUtils.LONG;
            case OpcodeConst.DLOAD:
            case OpcodeConst.DLOAD_0:
            case OpcodeConst.DLOAD_1:
            case OpcodeConst.DLOAD_2:
            case OpcodeConst.DLOAD_3:
            case OpcodeConst.DSTORE:
            case OpcodeConst.DSTORE_0:
            case OpcodeConst.DSTORE_1:
            case OpcodeConst.DSTORE_2:
            case OpcodeConst.DSTORE_3:
                return TypeUtils.DOUBLE;
            case OpcodeConst.FLOAD:
            case OpcodeConst.FLOAD_0:
            case OpcodeConst.FLOAD_1:
            case OpcodeConst.FLOAD_2:
            case OpcodeConst.FLOAD_3:
            case OpcodeConst.FSTORE:
            case OpcodeConst.FSTORE_0:
            case OpcodeConst.FSTORE_1:
            case OpcodeConst.FSTORE_2:
            case OpcodeConst.FSTORE_3:
                return TypeUtils.FLOAT;
            case OpcodeConst.ALOAD:
            case OpcodeConst.ALOAD_0:
            case OpcodeConst.ALOAD_1:
            case OpcodeConst.ALOAD_2:
            case OpcodeConst.ALOAD_3:
            case OpcodeConst.ASTORE:
            case OpcodeConst.ASTORE_0:
            case OpcodeConst.ASTORE_1:
            case OpcodeConst.ASTORE_2:
            case OpcodeConst.ASTORE_3:
                return TypeUtils.OBJECT;
            default:
                throw new RuntimeException("Oops: unknown case in switch" + opcode);
        }
    }
    // endregion
}
