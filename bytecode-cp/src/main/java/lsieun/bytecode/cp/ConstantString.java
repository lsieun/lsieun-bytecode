package lsieun.bytecode.cp;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;

public final class ConstantString extends Constant {
    public final int string_index;

    ConstantString(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_String);
        byte[] bytes = byteDashboard.peekN(3);
        super.setBytes(bytes);

        byteDashboard.skip(1);
        this.string_index = byteDashboard.readUnsignedShort();
        super.value = "#" + string_index;
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantString(this);
    }

}
