package lsieun.bytecode.f_opcode;

import lsieun.bytecode.classfile.attrs.method.Code;
import lsieun.bytecode.classfile.clazz.MethodInfo;
import lsieun.bytecode.classfile.utils.AttributeUtils;
import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.utils.InstructionChain;
import lsieun.bytecode.opcode.visitors.OpcodeReadVisitor;
import lsieun.utils.ClassReader;

public class A_Simple_Opcode {
    public static void main(String[] args) {
        // 第1步，获取code_bytes
        MethodInfo methodInfo = ClassReader.readMethod();
        Code codeAttribute = AttributeUtils.findCodeAttribute(methodInfo);
        byte[] code_bytes = codeAttribute.code;

        // 第2步，由code_bytes构建Instruction
        OpcodeReadVisitor rv = new OpcodeReadVisitor(code_bytes);
        InstructionChain chain = rv.getInstructionChain();

        // 第3步，对Instruction进行展示
        System.out.println("\r\n=== === ===  === === ===  === === ===");
        String format = "%04d: %-20s";

        Instruction current = chain.start;
        while(current != null) {
            int opcode = current.opcode;
            int offset = current.pos;
            String opcodeName = OpcodeConst.getOpcodeName(opcode);
            String line = String.format(format, offset, opcodeName);
            System.out.println(line);
            current = current.next;
        }

    }
}
