package lsieun.bytecode.classfile.attrs.method;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.classfile.attrs.annotation.ElementValue;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class AnnotationDefault extends AttributeInfo {
    public final ElementValue default_value;

    public AnnotationDefault(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);
        this.default_value = ElementValue.readElementValue(byteDashboard, constantPool);
    }

    @Override
    public void accept(Visitor v) {
        v.visitAnnotationDefault(this);
    }
}
