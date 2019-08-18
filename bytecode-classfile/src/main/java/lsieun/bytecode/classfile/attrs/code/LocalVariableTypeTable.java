package lsieun.bytecode.classfile.attrs.code;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public final class LocalVariableTypeTable extends AttributeInfo {
    private final int local_variable_type_table_length;
    private final LocalVariableType[] entries;

    public LocalVariableTypeTable(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);

        this.local_variable_type_table_length = byteDashboard.readUnsignedShort();
        this.entries = new LocalVariableType[local_variable_type_table_length];

        for (int i = 0; i < local_variable_type_table_length; i++) {
            LocalVariableType item = new LocalVariableType(byteDashboard, constantPool);
            this.entries[i] = item;
        }
    }

    public int getLocalVariableTypeTableLength() {
        return local_variable_type_table_length;
    }

    public LocalVariableType[] getEntries() {
        return entries;
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitLocalVariableTypeTable(this);
    }

}
