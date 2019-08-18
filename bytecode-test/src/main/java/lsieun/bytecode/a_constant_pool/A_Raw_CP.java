package lsieun.bytecode.a_constant_pool;

import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.cp.visitor.CPRawVisitor;
import lsieun.utils.ClassReader;

public class A_Raw_CP {

    public static void main(String[] args) {
        ClassFile classFile = ClassReader.readClassFile();
        ConstantPool constantPool = classFile.constantPool;

        CPRawVisitor visitor = new CPRawVisitor();
        constantPool.accept(visitor);
    }
}
