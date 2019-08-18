package lsieun.bytecode.core;

import lsieun.bytecode.core.cst.JVMConst;
import lsieun.bytecode.core.exceptions.ClassFormatException;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.ByteUtils;

public class MagicNumber extends Node {
    public MagicNumber(final ByteDashboard byteDashboard) {
        byte[] bytes = byteDashboard.nextN(4);
        super.setBytes(bytes);

        int magic = ByteUtils.bytesToInt(bytes, 0);
        if (magic != JVMConst.JVM_CLASSFILE_MAGIC) {
            throw new ClassFormatException("It is not a Java .class file");
        }
    }

    public void accept(Visitor v) {
        v.visitMagicNumber(this);
    }

}
