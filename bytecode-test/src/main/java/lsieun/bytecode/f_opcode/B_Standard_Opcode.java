package lsieun.bytecode.f_opcode;

import lsieun.bytecode.classfile.utils.AttributeUtils;
import lsieun.bytecode.opcode.utils.InstructionChain;
import lsieun.bytecode.opcode.visitors.CodeStandardVisitor;
import lsieun.bytecode.opcode.visitors.OpcodeReadVisitor;
import lsieun.utils.ClassReader;
import lsieun.utils.radix.HexUtils;

public class B_Standard_Opcode {
    public static void main(String[] args) {
        // 第1步，获取code_bytes
        byte[] code_bytes = AttributeUtils.findCodeAttribute(ClassReader.readMethod()).code;

        // 第2步，由code_bytes构建Instruction
        OpcodeReadVisitor rv = new OpcodeReadVisitor(code_bytes);
        InstructionChain chain = rv.getInstructionChain();
        System.out.println("\r\n=== === ===  === === ===  === === ===");
        System.out.println("HexCode: " + HexUtils.fromBytes(code_bytes));

        // 第3步，对Instruction进行展示
        System.out.println("\r\n=== === ===  === === ===  === === ===");
        CodeStandardVisitor v = new CodeStandardVisitor();
        v.visit(chain, code_bytes);
    }
}
