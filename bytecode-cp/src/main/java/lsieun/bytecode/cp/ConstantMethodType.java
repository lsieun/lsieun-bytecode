package lsieun.bytecode.cp;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;

public final class ConstantMethodType extends Constant {
    public final int descriptor_index;

    ConstantMethodType(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_MethodType);
        byte[] bytes = byteDashboard.peekN(3);
        super.setBytes(bytes);

        byteDashboard.skip(1);
        this.descriptor_index = byteDashboard.readUnsignedShort();
        super.value = "#" + descriptor_index;
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantMethodType(this);
    }
}
