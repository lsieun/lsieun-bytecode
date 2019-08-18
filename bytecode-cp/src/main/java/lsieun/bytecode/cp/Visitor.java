package lsieun.bytecode.cp;

import lsieun.bytecode.core.CompilerVersion;
import lsieun.bytecode.core.MagicNumber;

public interface Visitor {



    void visitConstantPool(ConstantPool obj);

    // region constant pool
    void visitConstant(Constant obj);

    void visitConstantUtf8(ConstantUtf8 obj);

    void visitConstantInteger(ConstantInteger obj);

    void visitConstantFloat(ConstantFloat obj);

    void visitConstantLong(ConstantLong obj);

    void visitConstantDouble(ConstantDouble obj);

    void visitConstantClass(ConstantClass obj);

    void visitConstantString(ConstantString obj);

    void visitConstantFieldref(ConstantFieldref obj);

    void visitConstantMethodref(ConstantMethodref obj);

    void visitConstantInterfaceMethodref(ConstantInterfaceMethodref obj);

    void visitConstantNameAndType(ConstantNameAndType obj);

    void visitConstantMethodHandle(ConstantMethodHandle obj);

    void visitConstantMethodType(ConstantMethodType obj);

    void visitConstantDynamic(ConstantDynamic obj);

    void visitConstantInvokeDynamic(ConstantInvokeDynamic obj);

    void visitConstantModule(ConstantModule obj);

    void visitConstantPackage(ConstantPackage obj);
    // endregion
}
