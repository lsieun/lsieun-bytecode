package lsieun.bytecode.classfile.attrs.annotation;

import java.util.ArrayList;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public final class ArrayElementValue extends ElementValue {
    public final int num_values;
    public final ElementValue[] entries;
    private final String value;

    public ArrayElementValue(ByteDashboard byteDashboard, final ConstantPool constantPool) {
        super(byteDashboard);

        this.num_values = byteDashboard.readUnsignedShort();
        this.entries = new ElementValue[num_values];

        for (int i = 0; i < num_values; i++) {
            ElementValue item = ElementValue.readElementValue(byteDashboard, constantPool);
            this.entries[i] = item;
        }

        this.value = "//FIXME: 这里value不对啊";
    }

    @Override
    public String stringifyValue() {
        return this.value;
    }
}
