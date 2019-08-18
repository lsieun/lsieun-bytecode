package lsieun.bytecode.classfile.attrs;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;

public final class Signature extends AttributeInfo {
    public final int signature_index;
    private final String value;

    public Signature(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);

        this.signature_index = byteDashboard.readUnsignedShort();
        this.value = constantPool.getConstantString(signature_index, CPConst.CONSTANT_Utf8);
    }

    public String getValue() {
        return value;
    }

    @Override
    public void accept(Visitor v) {
        v.visitSignature(this);
    }
}
