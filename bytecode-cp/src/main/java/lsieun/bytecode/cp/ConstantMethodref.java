package lsieun.bytecode.cp;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.cp.facet.ConstantRef;
import lsieun.utils.ByteDashboard;

public final class ConstantMethodref extends Constant implements ConstantRef {
    public int class_index;
    public int name_and_type_index;

    public ConstantMethodref(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_Methodref);
        byte[] bytes = byteDashboard.peekN(5);
        super.setBytes(bytes);

        byteDashboard.skip(1);
        this.class_index = byteDashboard.readUnsignedShort();
        this.name_and_type_index = byteDashboard.readUnsignedShort();

        super.value = "#" + class_index + ".#" + name_and_type_index;
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantMethodref(this);
    }

    @Override
    public int getClassIndex() {
        return class_index;
    }

    @Override
    public void setClassIndex(int class_index) {
        this.class_index = class_index;
    }

    @Override
    public int getNameAndTypeIndex() {
        return name_and_type_index;
    }

    @Override
    public void setNameAndTypeIndex(int name_and_type_index) {
        this.name_and_type_index = name_and_type_index;
    }

}
