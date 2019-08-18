package lsieun.bytecode.f_opcode;

import lsieun.bytecode.classfile.attrs.method.Code;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.clazz.MethodInfo;
import lsieun.bytecode.classfile.utils.AttributeUtils;
import lsieun.bytecode.cp.utils.ConstantPoolUtils;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.opcode.utils.InstructionChain;
import lsieun.bytecode.f_opcode.visitors.BaroqueOpcodeVisitor;
import lsieun.bytecode.opcode.visitors.OpcodeReadVisitor;
import lsieun.utils.ClassReader;
import lsieun.utils.radix.HexUtils;

public class C_Baroque_Opcode {
    public static void main(String[] args) {
        // 第1步，获取数据，获取ClassFile
        ClassFile classFile = ClassReader.readClassFile();

        // 第2步，获取数据，由ClassFile获取constantPool和code_bytes
        ConstantPool constantPool = classFile.constantPool;
        ConstantPoolUtils.simplify(constantPool);

        MethodInfo methodInfo = ClassReader.readMethod(classFile);
        Code codeAttribute = AttributeUtils.findCodeAttribute(methodInfo);
        byte[] code_bytes = codeAttribute.code;
        OpcodeReadVisitor rv = new OpcodeReadVisitor(code_bytes);
        InstructionChain chain = rv.getInstructionChain();

        // 第3步，展示结果
        System.out.println("\r\n");
        System.out.println("=== === ===  === === ===  === === ===");
        System.out.println("HexCode: " + HexUtils.fromBytes(code_bytes));
        System.out.println("=== === ===  === === ===  === === ===");
        BaroqueOpcodeVisitor v = new BaroqueOpcodeVisitor();
        v.visit(constantPool, code_bytes, chain);
    }
}
