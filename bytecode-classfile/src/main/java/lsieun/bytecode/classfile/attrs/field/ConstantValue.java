package lsieun.bytecode.classfile.attrs.field;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.utils.ByteDashboard;

public final class ConstantValue extends AttributeInfo {
    private final int constantvalue_index;
    private final String value;

    public ConstantValue(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);

        this.constantvalue_index = byteDashboard.readUnsignedShort();
        this.value = constantPool.getConstant(constantvalue_index).value;
    }

    public int getConstantValueIndex() {
        return constantvalue_index;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantValue(this);
    }
}
