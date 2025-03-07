package lsieun.bytecode.d_method;

import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.cp.utils.ConstantPoolUtils;
import lsieun.bytecode.cp.visitor.CPStandardVisitor;
import lsieun.bytecode.d_method.visitors.MethodCodeVisitor;
import lsieun.utils.ClassReader;
import lsieun.utils.PropertyUtils;

public class C_Code_Locals {
    public static void main(String[] args) {
        ClassFile classFile = ClassReader.readClassFile();
        String name_and_type = PropertyUtils.getProperty("bytecode.content.method.signature");

        ConstantPool constantPool = classFile.constantPool;
        ConstantPoolUtils.simplify(constantPool);
        CPStandardVisitor cpV = new CPStandardVisitor();
        constantPool.accept(cpV);

        System.out.println(System.lineSeparator());
        System.out.println("=== === ===  === === ===  === === ===");
        MethodCodeVisitor v = new MethodCodeVisitor(name_and_type);
        v.visitClassFile(classFile);
    }
}
