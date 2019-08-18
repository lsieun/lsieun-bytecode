package lsieun.bytecode.classfile.clazz;

import lsieun.bytecode.core.Node;
import lsieun.bytecode.classfile.Visitor;
import lsieun.utils.ByteDashboard;

public class ClassInfo extends Node {
    public final int access_flags;
    public final int this_class;
    public final int super_class;
    public final int interfaces_count;
    public final int[] interfaces;

    public ClassInfo(final ByteDashboard byteDashboard) {
        int interfaces_count = byteDashboard.peekInt(6, 2);
        byte[] bytes = byteDashboard.peekN(8 + interfaces_count * 2);
        super.setBytes(bytes);

        this.access_flags = byteDashboard.readUnsignedShort();
        this.this_class = byteDashboard.readUnsignedShort();
        this.super_class = byteDashboard.readUnsignedShort();
        this.interfaces_count = byteDashboard.readUnsignedShort();
        this.interfaces = new int[interfaces_count];

        for (int i = 0; i < interfaces_count; i++) {
            interfaces[i] = byteDashboard.readUnsignedShort();
        }
    }

    public void accept(Visitor v) {
        v.visitClassInfo(this);
    }

}
