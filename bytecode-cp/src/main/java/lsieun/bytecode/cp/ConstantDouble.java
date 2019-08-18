package lsieun.bytecode.cp;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.ByteUtils;

public final class ConstantDouble extends Constant {
    public final Double doubleValue;

    public ConstantDouble(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_Double);
        byte[] bytes = byteDashboard.peekN(9);
        super.setBytes(bytes);

        byte[] value_bytes = byteDashboard.nextN(1, 8);
        this.doubleValue = ByteUtils.toDouble(value_bytes);
        super.value = String.valueOf(this.doubleValue);
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantDouble(this);
    }

}
