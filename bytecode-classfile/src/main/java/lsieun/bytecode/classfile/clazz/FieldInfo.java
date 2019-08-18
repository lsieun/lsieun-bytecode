package lsieun.bytecode.classfile.clazz;

import lsieun.bytecode.core.Node;
import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.core.cst.AccessConst;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.ByteUtils;

public final class FieldInfo extends Node {
    public final int access_flags;
    public final int name_index;
    public final int descriptor_index;
    public final Attributes attributes;
    private String value;

    public FieldInfo(ByteDashboard byteDashboard, ConstantPool constantPool) {
        byte[] bytes = byteDashboard.peekN(8);

        this.access_flags = byteDashboard.readUnsignedShort();
        this.name_index = byteDashboard.readUnsignedShort();
        this.descriptor_index = byteDashboard.readUnsignedShort();
        //this.value = "#" + name_index + ":#" + descriptor_index;

        String name = constantPool.getConstantString(name_index, CPConst.CONSTANT_Utf8);
        String descriptor = constantPool.getConstantString(descriptor_index, CPConst.CONSTANT_Utf8);
        this.value = name + ":" + descriptor;

        this.attributes = new Attributes(byteDashboard, constantPool);
        for (int i = 0; i < attributes.getCount(); i++) {
            AttributeInfo attr = attributes.getEntries()[i];
            bytes = ByteUtils.merge(bytes, attr.getBytes());
        }
        // 设置bytes
        super.setBytes(bytes);
    }

    public String getAccessFlagsString() {
        return AccessConst.getFieldAccessFlagsString(access_flags);
    }

    public int getAttributesCount() {
        return attributes.getCount();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void accept(Visitor v) {
        v.visitFieldInfo(this);
    }

}
