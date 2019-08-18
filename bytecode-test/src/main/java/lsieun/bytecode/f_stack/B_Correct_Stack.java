package lsieun.bytecode.f_stack;

import lsieun.bytecode.classfile.clazz.*;
import lsieun.bytecode.cp.utils.ConstantPoolUtils;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.f_stack.visitors.StackMapFrameVisitor2;
import lsieun.utils.ClassReader;

public class B_Correct_Stack {
    public static void main(String[] args) {
        // 第1步，获取数据，获取ClassFile
        ClassFile classFile = ClassReader.readClassFile();

        // 第2步，获取数据，由ClassFile获取constantPool和code_bytes
        ConstantPool constantPool = classFile.constantPool;
        ConstantPoolUtils.simplify(constantPool);

        MethodInfo methodInfo = ClassReader.readMethod(classFile);
//        byte[] code_bytes = AttributeUtils.findCodeAttribute(methodInfo).getCode();
//        Code codeAttribute = AttributeUtils.findCodeAttribute(methodInfo);
//        int maxLocals = codeAttribute.getMaxLocals();
//        int maxStack = codeAttribute.getMaxStack();


        // 第3步，展示结果
        System.out.println("\r\n");
        System.out.println("=== === ===  === === ===  === === ===");
//        InstructionChain instructionList = new InstructionChain(code_bytes);
//        int count = instructionList.getCount();
//        Instruction[] instructions = instructionList.getInstructions();
//
//        Frame frame = new Frame(maxLocals, maxStack);
//
//        ExecutionVisitor v = new ExecutionVisitor();
//        v.setFrame(frame);
//        v.setConstantPool(constantPool);
//        for(int i=0; i<count; i++) {
//            instructions[i].accept(v);
//            displayFrame(frame);
//        }

        StackMapFrameVisitor2 v = new StackMapFrameVisitor2();
        v.visit(methodInfo, constantPool);
    }


}
