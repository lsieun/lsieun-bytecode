package lsieun.bytecode.classfile.attrs.annotation;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public final class AnnotationEntry {
    public final int type_index;
    public final int num_element_value_pairs;
    public final ElementValuePair[] element_value_pair_list;
    public final String value;

    public AnnotationEntry(ByteDashboard byteDashboard, ConstantPool constantPool) {
        this.type_index = byteDashboard.readUnsignedShort();
        this.num_element_value_pairs = byteDashboard.readUnsignedShort();
        this.element_value_pair_list = new ElementValuePair[num_element_value_pairs];

        for (int i = 0; i < num_element_value_pairs; i++) {
            ElementValuePair item = new ElementValuePair(byteDashboard, constantPool);
            this.element_value_pair_list[i] = item;
        }

        String type = constantPool.getConstantString(type_index, CPConst.CONSTANT_Utf8);
        this.value = type;
    }

    public String getValue() {
        return value;
    }
}
