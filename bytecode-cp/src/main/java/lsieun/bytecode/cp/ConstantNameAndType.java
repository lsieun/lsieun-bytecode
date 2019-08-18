package lsieun.bytecode.cp;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;

public final class ConstantNameAndType extends Constant {
    public final int name_index;
    public final int descriptor_index;

    ConstantNameAndType(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_NameAndType);
        byte[] bytes = byteDashboard.peekN(5);
        super.setBytes(bytes);

        byteDashboard.skip(1);
        this.name_index = byteDashboard.readUnsignedShort();
        this.descriptor_index = byteDashboard.readUnsignedShort();

        super.value = "#" + name_index + ":#" + descriptor_index;
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantNameAndType(this);
    }

}
