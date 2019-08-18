package lsieun.bytecode.classfile.attrs.method;

import lsieun.utils.ByteDashboard;

public class MethodParameter {
    public final int name_index;
    public final int access_flags;

    public MethodParameter(ByteDashboard byteDashboard) {
        this.name_index = byteDashboard.readUnsignedShort();
        this.access_flags = byteDashboard.readUnsignedShort();
    }

}
