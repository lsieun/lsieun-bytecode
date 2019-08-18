package lsieun.bytecode.classfile.clazz;

import lsieun.bytecode.core.Node;
import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class Attributes extends Node {
    private final int attributes_count;
    private final AttributeInfo[] entries;

    public Attributes(ByteDashboard byteDashboard, ConstantPool constantPool) {
        byte[] bytes = byteDashboard.peekN(2);
        super.setBytes(bytes);

        this.attributes_count = byteDashboard.readUnsignedShort();
        this.entries = new AttributeInfo[attributes_count];

        for (int i = 0; i < attributes_count; i++) {
            AttributeInfo attr = AttributeInfo.read(byteDashboard, constantPool);
            this.entries[i] = attr;
        }
    }

    public int getCount() {
        return attributes_count;
    }

    public AttributeInfo[] getEntries() {
        return entries;
    }

    public void accept(Visitor v) {
        v.visitAttributes(this);
    }

}
