package lsieun.bytecode.cp;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.ByteUtils;

public final class ConstantLong extends Constant {
    public final Long longValue;

    ConstantLong(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_Long);
        byte[] bytes = byteDashboard.peekN(9);
        super.setBytes(bytes);

        byte[] value_bytes = byteDashboard.nextN(1, 8);
        this.longValue = ByteUtils.toLong(value_bytes);
        super.value = String.valueOf(this.longValue);
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantLong(this);
    }

}
