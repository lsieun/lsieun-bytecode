package lsieun.bytecode.e_attribute;

import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.clazz.FieldInfo;
import lsieun.bytecode.classfile.utils.AttributeUtils;
import lsieun.bytecode.classfile.utils.FieldUtils;
import lsieun.bytecode.classfile.visitors.AttributeStandardVisitor;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.cp.visitor.CPStandardVisitor;
import lsieun.utils.ClassReader;
import lsieun.utils.PropertyUtils;

public class B_Field_Attribute {
    public static void main(String[] args) {
        ClassFile classFile = ClassReader.readClassFile();
        String name_and_type = PropertyUtils.getProperty("bytecode.content.field.signature");
        String attrName = PropertyUtils.getProperty("bytecode.content.field.attribute.name");

        FieldInfo field = FieldUtils.findField(classFile, name_and_type);
        AttributeInfo attribute = AttributeUtils.findAttribute(field.attributes, attrName);

        ConstantPool constantPool = classFile.constantPool;
        CPStandardVisitor cpV = new CPStandardVisitor();
        constantPool.accept(cpV);

        AttributeStandardVisitor visitor = new AttributeStandardVisitor(constantPool);
        attribute.accept(visitor);
    }
}
