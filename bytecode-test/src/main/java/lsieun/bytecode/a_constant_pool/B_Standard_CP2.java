package lsieun.bytecode.a_constant_pool;

import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.cp.utils.ConstantPoolUtils;
import lsieun.bytecode.cp.visitor.CPStandardVisitor;
import lsieun.utils.ClassReader;

public class B_Standard_CP2 {
    public static void main(String[] args) {
        ClassFile classFile = ClassReader.readClassFile();
        ConstantPool constantPool = classFile.constantPool;
        ConstantPoolUtils.simplify(constantPool);

        CPStandardVisitor visitor = new CPStandardVisitor();
        constantPool.accept(visitor);
    }
}
