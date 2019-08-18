package lsieun.bytecode.classfile.attrs.annotation;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public final class SimpleElementValue extends ElementValue {
    private final int const_value_index;
    private final String value;

    public SimpleElementValue(ByteDashboard byteDashboard, final ConstantPool constantPool) {
        super(byteDashboard);

        this.const_value_index = byteDashboard.readUnsignedShort();
        this.value = constantPool.getConstant(const_value_index).value;
    }

    @Override
    public String stringifyValue() {
        return this.value;
    }
}
