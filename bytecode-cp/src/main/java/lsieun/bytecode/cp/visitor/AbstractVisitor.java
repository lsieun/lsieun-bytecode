package lsieun.bytecode.cp.visitor;

import lsieun.bytecode.cp.*;

public class AbstractVisitor implements Visitor {

    @Override
    public void visitConstantPool(ConstantPool obj) {

    }

    // region constant pool
    @Override
    public void visitConstant(Constant obj) {

    }

    @Override
    public void visitConstantUtf8(ConstantUtf8 obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantInteger(ConstantInteger obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantFloat(ConstantFloat obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantLong(ConstantLong obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantDouble(ConstantDouble obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantClass(ConstantClass obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantString(ConstantString obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantFieldref(ConstantFieldref obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantMethodref(ConstantMethodref obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantInterfaceMethodref(ConstantInterfaceMethodref obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantNameAndType(ConstantNameAndType obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantMethodHandle(ConstantMethodHandle obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantMethodType(ConstantMethodType obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantDynamic(ConstantDynamic obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantInvokeDynamic(ConstantInvokeDynamic obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantModule(ConstantModule obj) {
        visitConstant(obj);
    }

    @Override
    public void visitConstantPackage(ConstantPackage obj) {
        visitConstant(obj);
    }
    // endregion
}
