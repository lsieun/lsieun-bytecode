package lsieun.bytecode.cp;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.ByteUtils;

public final class ConstantInteger extends Constant {
    public final Integer intValue;

    ConstantInteger(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_Integer);
        byte[] bytes = byteDashboard.peekN(5);
        super.setBytes(bytes);

        byte[] value_bytes = byteDashboard.nextN(1, 4);
        this.intValue = ByteUtils.toInt(value_bytes);
        super.value = String.valueOf(this.intValue);
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantInteger(this);
    }

}
