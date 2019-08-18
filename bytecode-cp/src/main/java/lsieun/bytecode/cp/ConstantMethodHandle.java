package lsieun.bytecode.cp;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;

public final class ConstantMethodHandle extends Constant {
    public final int reference_kind;
    public final int reference_index;

    ConstantMethodHandle(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_MethodHandle);
        byte[] bytes = byteDashboard.peekN(4);
        super.setBytes(bytes);

        byteDashboard.skip(1);
        this.reference_kind = byteDashboard.readUnsignedByte();
        this.reference_index = byteDashboard.readUnsignedShort();
        super.value = "#" + reference_index;
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantMethodHandle(this);
    }
}
