package lsieun.bytecode.classfile.attrs.annotation;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;

public final class EnumElementValue extends ElementValue {
    public final int type_name_index;
    public final int const_name_index;
    public final String value;

    public EnumElementValue(ByteDashboard byteDashboard, final ConstantPool constantPool) {
        super(byteDashboard);

        this.type_name_index = byteDashboard.readUnsignedShort();
        this.const_name_index = byteDashboard.readUnsignedShort();

        String type_name = constantPool.getConstantString(type_name_index, CPConst.CONSTANT_Utf8);
        String const_name = constantPool.getConstantString(const_name_index, CPConst.CONSTANT_Utf8);
        this.value = const_name + ":" + type_name;
    }

    @Override
    public String stringifyValue() {
        return this.value;
    }
}
