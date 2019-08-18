package lsieun.bytecode.classfile.attrs.method;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class MethodParameters extends AttributeInfo {
    public final int parameters_count;
    public final MethodParameter[] parameters;

    public MethodParameters(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);

        this.parameters_count = byteDashboard.readUnsignedByte();
        this.parameters = new MethodParameter[parameters_count];
        for(int i=0; i<parameters_count; i++) {
            this.parameters[i] = new MethodParameter(byteDashboard);
        }
    }

    public void accept(Visitor v) {
        v.visitMethodParameters(this);
    }
}
