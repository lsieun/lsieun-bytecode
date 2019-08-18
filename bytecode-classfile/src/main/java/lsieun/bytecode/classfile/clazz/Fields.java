package lsieun.bytecode.classfile.clazz;

import lsieun.bytecode.core.Node;
import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class Fields extends Node {
    public final int fields_count;
    public final FieldInfo[] entries;

    public Fields(final ByteDashboard byteDashboard, ConstantPool constantPool) {
        byte[] bytes = byteDashboard.peekN(2);
        super.setBytes(bytes);

        this.fields_count = byteDashboard.readUnsignedShort();
        this.entries = new FieldInfo[fields_count];
        for (int i = 0; i < fields_count; i++) {
            FieldInfo fieldInfo = new FieldInfo(byteDashboard, constantPool);
            this.entries[i] = fieldInfo;
        }
    }

    public void accept(Visitor v) {
        v.visitFields(this);
    }

}
