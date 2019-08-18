package lsieun.bytecode.classfile.attrs.annotation;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;

public final class ClassElementValue extends ElementValue {
    public final int class_info_index;
    public final String value;

    public ClassElementValue(ByteDashboard byteDashboard, final ConstantPool constantPool) {
        super(byteDashboard);

        this.class_info_index = byteDashboard.readUnsignedShort();

        this.value = constantPool.getConstantString(class_info_index, CPConst.CONSTANT_Utf8);
    }

    @Override
    public String stringifyValue() {
        return this.value;
    }
}
