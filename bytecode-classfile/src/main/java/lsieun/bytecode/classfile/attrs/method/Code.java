package lsieun.bytecode.classfile.attrs.method;

import lsieun.bytecode.classfile.clazz.Attributes;
import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.ByteUtils;

public final class Code extends AttributeInfo {
    public int max_stack;
    public int max_locals;
    public int code_length;
    public byte[] code;
    public int exception_table_length;
    public ExceptionTable[] exception_table_array;
    public Attributes attributes;

    public Code(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);

        // 第一部分
        this.max_stack = byteDashboard.readUnsignedShort();
        this.max_locals = byteDashboard.readUnsignedShort();
        this.code_length = byteDashboard.readInt();
        this.code = byteDashboard.nextN(code_length);

        // 第二部分
        byte[] exception_table_length_bytes = byteDashboard.nextN(2);
        this.exception_table_length = ByteUtils.bytesToInt(exception_table_length_bytes, 0);
        this.exception_table_array = new ExceptionTable[exception_table_length];
        for (int i = 0; i < exception_table_length; i++) {
            ExceptionTable item = new ExceptionTable(byteDashboard, constantPool);
            this.exception_table_array[i] = item;
        }

        // 第三部分
        this.attributes = new Attributes(byteDashboard, constantPool);
    }

    public int getAttributesCount() {
        return attributes.getCount();
    }

    @Override
    public void accept(Visitor v) {
        v.visitCode(this);
    }
}
