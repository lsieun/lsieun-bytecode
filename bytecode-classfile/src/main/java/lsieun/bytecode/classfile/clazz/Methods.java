package lsieun.bytecode.classfile.clazz;

import lsieun.bytecode.core.Node;
import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public final class Methods extends Node {
    public final int methods_count;
    public final MethodInfo[] entries;

    public Methods(final ByteDashboard byteDashboard, ConstantPool constantPool) {
        byte[] bytes = byteDashboard.peekN(2);
        super.setBytes(bytes);

        this.methods_count = byteDashboard.readUnsignedShort();
        this.entries = new MethodInfo[methods_count];
        for (int i = 0; i < methods_count; i++) {
            MethodInfo methodInfo = new MethodInfo(byteDashboard, constantPool);
            this.entries[i] = methodInfo;
        }
    }

    public void accept(Visitor v) {
        v.visitMethods(this);
    }

}
