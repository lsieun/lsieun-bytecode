package lsieun.bytecode.classfile.attrs.code;

import lsieun.utils.ByteDashboard;

public class LineNumber {
    private final int start_pc;
    private final int line_number;

    public LineNumber(ByteDashboard byteDashboard) {
        this.start_pc = byteDashboard.readUnsignedShort();
        this.line_number = byteDashboard.readUnsignedShort();
    }

    public int getStartPC() {
        return start_pc;
    }

    public int getLineNumber() {
        return line_number;
    }
}
