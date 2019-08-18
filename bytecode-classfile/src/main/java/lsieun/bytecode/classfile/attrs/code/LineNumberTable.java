package lsieun.bytecode.classfile.attrs.code;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.ByteUtils;

public final class LineNumberTable extends AttributeInfo {
    private final int line_number_table_length;
    private final LineNumber[] line_number_array;

    public LineNumberTable(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);

        byte[] line_number_table_length_bytes = byteDashboard.nextN(2);
        this.line_number_table_length = ByteUtils.bytesToInt(line_number_table_length_bytes, 0);

        this.line_number_array = new LineNumber[line_number_table_length];
        for (int i = 0; i < line_number_table_length; i++) {
            LineNumber item = new LineNumber(byteDashboard);
            this.line_number_array[i] = item;
        }
    }

    public int getLineNumberTableLength() {
        return line_number_table_length;
    }

    public LineNumber[] getLineNumberArray() {
        return line_number_array;
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitLineNumberTable(this);
    }
}
