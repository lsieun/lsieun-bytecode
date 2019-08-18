package lsieun.bytecode.classfile.attrs.classfile;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class EnclosingMethod extends AttributeInfo {
    public final int class_index;
    public final int method_index;

    public EnclosingMethod(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);

        this.class_index = byteDashboard.readUnsignedShort();
        this.method_index = byteDashboard.readUnsignedShort();
    }

    @Override
    public void accept(Visitor v) {
        v.visitEnclosingMethod(this);
    }
}
