package lsieun.bytecode.e_attribute;

import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.utils.AttributeUtils;
import lsieun.bytecode.classfile.visitors.AttributeStandardVisitor;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.cp.visitor.CPStandardVisitor;
import lsieun.utils.ClassReader;
import lsieun.utils.PropertyUtils;

public class A_Class_Attribute {
    public static void main(String[] args) {
        ClassFile classFile = ClassReader.readClassFile();
        String attrName = PropertyUtils.getProperty("bytecode.content.classfile.attribute.name");
        AttributeInfo attribute = AttributeUtils.findAttribute(classFile.attributes, attrName);

        ConstantPool constantPool = classFile.constantPool;
        CPStandardVisitor cpV = new CPStandardVisitor();
        constantPool.accept(cpV);

        AttributeStandardVisitor visitor = new AttributeStandardVisitor(constantPool);
        attribute.accept(visitor);
    }
}
