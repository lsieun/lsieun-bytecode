package lsieun.bytecode.classfile.attrs.classfile;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public final class SourceFile extends AttributeInfo {
    public final int sourcefile_index;

    public SourceFile(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);
        this.sourcefile_index = byteDashboard.readUnsignedShort();

        String value = constantPool.getConstantString(sourcefile_index, CPConst.CONSTANT_Utf8);
        super.setValue(value);
    }

    @Override
    public void accept(Visitor v) {
        v.visitSourceFile(this);
    }

}
