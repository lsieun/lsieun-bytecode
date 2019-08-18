package lsieun.bytecode.classfile.attrs.classfile;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class BootstrapMethods extends AttributeInfo {
    public final int num_bootstrap_methods;
    public final BootstrapMethod[] entries;

    public BootstrapMethods(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);

        this.num_bootstrap_methods = byteDashboard.readUnsignedShort();
        this.entries = new BootstrapMethod[num_bootstrap_methods];
        for (int i = 0; i < num_bootstrap_methods; i++) {
            this.entries[i] = new BootstrapMethod(byteDashboard);
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitBootstrapMethods(this);
    }
}
