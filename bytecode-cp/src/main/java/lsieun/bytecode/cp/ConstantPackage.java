package lsieun.bytecode.cp;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;

public final class ConstantPackage extends Constant {
    public final int name_index;

    ConstantPackage(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_Package);
        byte[] bytes = byteDashboard.peekN(3);
        super.setBytes(bytes);

        byteDashboard.skip(1);
        this.name_index = byteDashboard.readUnsignedShort();
        super.value = "#" + name_index;
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantPackage(this);
    }

}
