package lsieun.bytecode.cp;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;

public final class ConstantClass extends Constant {
    public final int name_index;

    public ConstantClass(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_Class);
        byte[] bytes = byteDashboard.peekN(3);
        super.setBytes(bytes);

        byteDashboard.skip(1);
        this.name_index = byteDashboard.readUnsignedShort();
        super.value = "#" + this.name_index;
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantClass(this);
    }
}
