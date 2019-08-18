package lsieun.bytecode.d_method;

import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.visitors.MethodRawVisitor;
import lsieun.utils.ClassReader;
import lsieun.utils.PropertyUtils;

public class A_Raw_Method {
    public static void main(String[] args) {
        ClassFile classFile = ClassReader.readClassFile();
        String name_and_type = PropertyUtils.getProperty("bytecode.content.method.signature");

        System.out.println("\r\n=== === ===  === === ===  === === ===");
        MethodRawVisitor v = new MethodRawVisitor(name_and_type);
        classFile.accept(v);
    }
}
