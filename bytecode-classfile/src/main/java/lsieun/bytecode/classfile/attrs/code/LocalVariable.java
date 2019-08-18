package lsieun.bytecode.classfile.attrs.code;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class LocalVariable implements Comparable<LocalVariable> {
    private final int start_pc; // Range in which the variable is valid
    private final int length;
    private final int name_index; // Index in constant pool of variable name
    private final int descriptor_index; // Index of variable signature
    private final int index; /* Variable is `index'th local variable on
     * this d_method's frame.
     */
    private final String nameAndType;

    public LocalVariable(ByteDashboard byteDashboard, ConstantPool constantPool) {
        this.start_pc = byteDashboard.readUnsignedShort();
        this.length = byteDashboard.readUnsignedShort();
        this.name_index = byteDashboard.readUnsignedShort();
        this.descriptor_index = byteDashboard.readUnsignedShort();
        this.index = byteDashboard.readUnsignedShort();

        String name = constantPool.getConstantString(name_index, CPConst.CONSTANT_Utf8);
        String descriptor = constantPool.getConstantString(descriptor_index, CPConst.CONSTANT_Utf8);
        this.nameAndType = name + ":" + descriptor;
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

    public int getDescriptorIndex() {
        return descriptor_index;
    }

    public int getIndex() {
        return index;
    }

    public String getNameAndType() {
        return nameAndType;
    }

    @Override
    public int compareTo(LocalVariable another) {
        int thisIndex = this.index;
        int anotherIndex = another.getIndex();
        return (thisIndex - anotherIndex);
    }
}
