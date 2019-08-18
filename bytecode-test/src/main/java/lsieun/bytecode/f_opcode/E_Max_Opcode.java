package lsieun.bytecode.f_opcode;

import lsieun.bytecode.classfile.attrs.method.Code;
import lsieun.bytecode.classfile.attrs.method.ExceptionTable;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.clazz.MethodInfo;
import lsieun.bytecode.classfile.utils.AttributeUtils;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.opcode.utils.InstructionChain;
import lsieun.bytecode.opcode.visitors.OpcodeReadVisitor;
import lsieun.utils.ClassReader;
import lsieun.utils.CodeMaxUtils;

public class E_Max_Opcode {
    public static void main(String[] args) {
        // 第1步，获取数据，获取ClassFile
        ClassFile classFile = ClassReader.readClassFile();

        // 第2步，获取数据，由ClassFile获取constantPool和code_bytes
        ConstantPool constant_pool = classFile.constantPool;

        MethodInfo methodInfo = ClassReader.readMethod(classFile);
        int access_flags = methodInfo.access_flags;
        int descriptor_index = methodInfo.descriptor_index;
        String signature = constant_pool.getConstantString(descriptor_index, CPConst.CONSTANT_Utf8);
        Type[] arg_types = TypeUtils.getArgumentTypes(signature);

        Code codeAttribute = AttributeUtils.findCodeAttribute(methodInfo);
        ExceptionTable[] exception_table_array = codeAttribute.exception_table_array;
        byte[] code_bytes = codeAttribute.code;
        OpcodeReadVisitor rv = new OpcodeReadVisitor(code_bytes);
        InstructionChain chain = rv.getInstructionChain();

        int max_locals = CodeMaxUtils.computeMaxLocals(access_flags, arg_types, chain);
        int max_stack = CodeMaxUtils.computeMaxStack(constant_pool, chain, exception_table_array);

        System.out.println("\r\n");
        System.out.println("=== === ===  === === ===  === === ===");
        System.out.println("max_locals: " + codeAttribute.max_locals);
        System.out.println("max_stack: " + codeAttribute.max_stack);
        System.out.println("max_locals: " + max_locals);
        System.out.println("max_stack: " + max_stack);
    }
}
