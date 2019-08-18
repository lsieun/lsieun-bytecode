package lsieun.bytecode.b_classfile;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.visitors.ClassFileRawVisitor;
import lsieun.utils.ClassReader;

public class A_Raw_ClassFile {
    public static void main(String[] args) {
        ClassFile classFile = ClassReader.readClassFile();

        System.out.println("\r\n");
        System.out.println("=== === ===  === === ===  === === ===");
        Visitor v = new ClassFileRawVisitor();
        classFile.accept(v);
    }
}
