package lsieun.bytecode.core;

import lsieun.utils.ByteDashboard;

public final class CompilerVersion extends Node {
    public final int minor_version;
    public final int major_version;

    public CompilerVersion(final ByteDashboard byteDashboard) {
        byte[] bytes = byteDashboard.peekN(4);
        super.setBytes(bytes);

        this.minor_version = byteDashboard.readUnsignedShort();
        this.major_version = byteDashboard.readUnsignedShort();
    }

    public void accept(Visitor v) {
        v.visitCompilerVersion(this);
    }

}
