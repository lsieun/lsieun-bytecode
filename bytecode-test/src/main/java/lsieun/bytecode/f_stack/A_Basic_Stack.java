package lsieun.bytecode.f_stack;

import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.clazz.MethodInfo;
import lsieun.bytecode.cp.utils.ConstantPoolUtils;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.f_stack.visitors.StackMapFrameVisitor;
import lsieun.utils.ClassReader;

public class A_Basic_Stack {
    public static void main(String[] args) {
        // 第1步，获取数据，获取ClassFile
        ClassFile classFile = ClassReader.readClassFile();

        // 第2步，获取数据，由ClassFile获取constantPool和code_bytes
        ConstantPool constantPool = classFile.constantPool;
        ConstantPoolUtils.simplify(constantPool);

        MethodInfo methodInfo = ClassReader.readMethod(classFile);

        // 第3步，展示结果
        System.out.println("\r\n");
//        System.out.println("=== === ===  === === ===  === === ===");
//        System.out.println("HexCode: " + HexUtils.fromBytes(code_bytes));
        System.out.println("=== === ===  === === ===  === === ===");
        StackMapFrameVisitor v = new StackMapFrameVisitor();
        v.visit(methodInfo, constantPool);
    }
}
