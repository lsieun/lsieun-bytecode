package lsieun.bytecode.classfile.attrs.classfile;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public final class InnerClasses extends AttributeInfo {
    public final int number_of_classes;
    public final InnerClass[] entries;

    public InnerClasses(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);

        this.number_of_classes = byteDashboard.readUnsignedShort();
        this.entries = new InnerClass[number_of_classes];

        for (int i = 0; i < number_of_classes; i++) {
            InnerClass item = new InnerClass(byteDashboard, constantPool);
            this.entries[i] = item;
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitInnerClasses(this);
    }

}
