package lsieun.bytecode.b_classfile;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.visitors.ClassFileStandardVisitor;
import lsieun.bytecode.cp.utils.ConstantPoolUtils;
import lsieun.utils.ByteDashboard;
import lsieun.utils.ClassReader;

public class B_Standard_ClassFile {
    public static void main(String[] args) {
        ByteDashboard byteDashboard = ClassReader.readBytes();
        ClassFile classFile = new ClassFile(byteDashboard);
        ConstantPoolUtils.simplify(classFile.constantPool);

        System.out.println(System.lineSeparator());
        System.out.println("=== === ===  === === ===  === === ===");
        Visitor v = new ClassFileStandardVisitor();
        classFile.accept(v);
    }
}
