package lsieun.bytecode.classfile.attrs.annotation;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class ElementValuePair {
    public final int element_name_index;
    public final ElementValue value;

    public ElementValuePair(ByteDashboard byteDashboard, ConstantPool constantPool) {
        this.element_name_index = byteDashboard.readUnsignedShort();
        this.value = ElementValue.readElementValue(byteDashboard, constantPool);
    }
}
