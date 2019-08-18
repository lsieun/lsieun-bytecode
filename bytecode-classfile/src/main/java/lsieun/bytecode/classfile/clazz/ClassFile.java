package lsieun.bytecode.classfile.clazz;

import lsieun.bytecode.core.CompilerVersion;
import lsieun.bytecode.core.MagicNumber;
import lsieun.bytecode.core.Node;
import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class ClassFile extends Node {
    public MagicNumber magicNumber;
    public CompilerVersion compilerVersion;
    public ConstantPool constantPool;
    public ClassInfo classInfo;
    public Fields fields;
    public Methods methods;
    public Attributes attributes;

    public ClassFile(final ByteDashboard byteDashboard) {
        this.magicNumber = new MagicNumber(byteDashboard);
        this.compilerVersion = new CompilerVersion(byteDashboard);
        this.constantPool = new ConstantPool(byteDashboard);
        this.classInfo = new ClassInfo(byteDashboard);
        this.fields = new Fields(byteDashboard, constantPool);
        this.methods = new Methods(byteDashboard, constantPool);
        this.attributes = new Attributes(byteDashboard, constantPool);
    }

    public void accept(Visitor v) {
        v.visitClassFile(this);
    }

}
