package lsieun.bytecode.c_field;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.visitors.FieldRawVisitor;
import lsieun.utils.ClassReader;
import lsieun.utils.PropertyUtils;

public class A_Raw_Field {
    public static void main(String[] args) {
        ClassFile classFile = ClassReader.readClassFile();
        String name_and_type = PropertyUtils.getProperty("bytecode.content.field.signature");

        System.out.println("\r\n=== === ===  === === ===  === === ===");
        Visitor v = new FieldRawVisitor(name_and_type);
        classFile.accept(v);
    }
}
