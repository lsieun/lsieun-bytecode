package lsieun.bytecode.classfile.attrs.code;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.utils.ByteDashboard;

public final class StackMapTable extends AttributeInfo {
    private final int number_of_entries;
    private final StackMapFrame[] entries;

    public StackMapTable(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);

        this.number_of_entries = byteDashboard.readUnsignedShort();
        this.entries = new StackMapFrame[number_of_entries];
        for (int i = 0; i < number_of_entries; i++) {
            this.entries[i] = new StackMapFrame(byteDashboard, constantPool);
        }
    }

    public int getNumberOfEntries() {
        return number_of_entries;
    }

    public StackMapFrame[] getEntries() {
        return entries;
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitStackMapTable(this);
    }
}
