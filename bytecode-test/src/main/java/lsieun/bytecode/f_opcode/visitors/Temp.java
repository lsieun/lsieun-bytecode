package lsieun.bytecode.f_opcode.visitors;

import lsieun.bytecode.core.exceptions.ClassGenException;
import lsieun.bytecode.core.type.ArrayType;
import lsieun.bytecode.core.type.ObjectType;
import lsieun.bytecode.core.type.ReferenceType;
import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.utils.TypeUtils;

public class Temp {
    /**
     * @return size of c_field (1 or 2)
     */
    protected int getFieldSize(final String signature) {
        return TypeUtils.size(TypeUtils.getTypeSizeAndCharNum(signature));
    }

    /**
     * @return return type of referenced c_field
     */
    public Type getType(final String signature) {
        return TypeUtils.getType(signature);
    }

    /**
     * @return type of c_field
     */
    public Type getFieldType(final String signature) {
        return TypeUtils.getType(signature);
    }

    /**
     * @return name of referenced c_field.
     */
    public String getFieldName(final String signature) {
        //return getName();
        return "";
    }

//    /**
//     * Also works for instructions whose f_stack effect depends on the
//     * constant pool entry they reference.
//     *
//     * @return Number of words consumed from f_stack by this opcode
//     */
//    @Override
//    public int consumeStack(final String signature) {
//        int sum;
//        if ((super.getOpcode() == OpcodeConst.INVOKESTATIC) || (super.getOpcode() == OpcodeConst.INVOKEDYNAMIC)) {
//            sum = 0;
//        } else {
//            sum = 1; // this reference
//        }
//
//        sum += TypeUtils.getArgumentTypesSize(signature);
//        return sum;
//    }

    /**
     * Also works for instructions whose f_stack effect depends on the
     * constant pool entry they reference.
     *
     * @return Number of words produced onto f_stack by this opcode
     */
    public int produceStack(final String signature) {
        return TypeUtils.getReturnTypeSize(signature);
    }

//    /**
//     * @return return type of referenced d_method.
//     */
//    public Type getType(final String signature) {
//        return getReturnType(signature);
//    }

//    /**
//     * @return name of referenced d_method.
//     */
//    public String getMethodName() {
//        return getName();
//    }

    /**
     * @return return type of referenced d_method.
     */
    public Type getReturnType(final String signature) {
        return TypeUtils.getReturnType(signature);
    }

    /**
     * @return argument types of referenced d_method.
     */
    public Type[] getArgumentTypes(final String signature) {
        return TypeUtils.getArgumentTypes(signature);
    }

    /**
     * Return the reference type representing the class, interface,
     * or array class referenced by the opcode.
     * @param internalName the ConstantPoolGen used to create the opcode
     * @return an ObjectType (if the referenced class type is a class
     *   or interface), or an ArrayType (if the referenced class
     *   type is an array class)
     */
    public ReferenceType getReferenceType(final String internalName) {
        String className = internalName;
        if (className.startsWith("[")) {
            return (ArrayType) TypeUtils.getType(className);
        }
        className = className.replace('/', '.');
        return TypeUtils.getInstance(className);
    }

    /**
     * Get the ObjectType of the d_method return or c_field.
     *
     * @return type of the referenced class/interface
     * @throws ClassGenException when the c_field is (or d_method returns) an array,
     */
    public ObjectType getLoadClassType(final String internalName) {
        final ReferenceType rt = getReferenceType(internalName);
        if(rt instanceof ObjectType) {
            return (ObjectType)rt;
        }
        throw new ClassGenException(rt.getSignature() + " does not represent an ObjectType");
    }
}
