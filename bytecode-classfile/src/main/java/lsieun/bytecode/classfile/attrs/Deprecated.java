package lsieun.bytecode.classfile.attrs;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.classfile.Visitor;
import lsieun.utils.ByteDashboard;

public final class Deprecated extends AttributeInfo {
    public Deprecated(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);
    }

    @Override
    public void accept(Visitor v) {
        v.visitDeprecated(this);
    }
}
