package lsieun.bytecode.classfile.attrs.code;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public final class LocalVariableTable extends AttributeInfo {
    private final int local_variable_table_length;
    private final LocalVariable[] entries;

    public LocalVariableTable(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);

        this.local_variable_table_length = byteDashboard.readUnsignedShort();
        this.entries = new LocalVariable[local_variable_table_length];

        for(int i = 0; i< local_variable_table_length; i++) {
            LocalVariable item = new LocalVariable(byteDashboard, constantPool);
            this.entries[i] = item;
        }
    }

    public int getLocalVariableTableLength() {
        return local_variable_table_length;
    }

    public LocalVariable[] getEntries() {
        return entries;
    }

    @Override
    public void accept(Visitor v) {
        v.visitLocalVariableTable(this);
    }
}
