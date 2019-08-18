package lsieun.bytecode.classfile.attrs.code;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class LocalVariableType implements Comparable<LocalVariableType> {
    private final int start_pc;
    private final int length;
    private final int name_index;
    private final int signature_index;
    private final int index;
    private final String nameAndType;

    public LocalVariableType(ByteDashboard byteDashboard, ConstantPool constantPool) {
        this.start_pc = byteDashboard.readUnsignedShort();
        this.length = byteDashboard.readUnsignedShort();
        this.name_index = byteDashboard.readUnsignedShort();
        this.signature_index = byteDashboard.readUnsignedShort();
        this.index = byteDashboard.readUnsignedShort();

        String name = constantPool.getConstantString(name_index, CPConst.CONSTANT_Utf8);
        String signature = constantPool.getConstantString(signature_index, CPConst.CONSTANT_Utf8);
        this.nameAndType = name + ":" + signature;
    }


    public int getStartPC() {
        return start_pc;
    }

    public int getLength() {
        return length;
    }

    public int getNameIndex() {
        return name_index;
    }

    public int getSignatureIndex() {
        return signature_index;
    }

    public int getIndex() {
        return index;
    }

    public String getNameAndType() {
        return nameAndType;
    }

    @Override
    public int compareTo(LocalVariableType another) {
        int thisIndex = this.index;
        int anotherIndex = another.getIndex();
        return (thisIndex - anotherIndex);
    }
}
