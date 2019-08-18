package lsieun.bytecode.cp;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.ByteUtils;

public final class ConstantFloat extends Constant {
    public final Float floatValue;

    ConstantFloat(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_Float);
        byte[] bytes = byteDashboard.peekN(5);
        super.setBytes(bytes);

        byte[] value_bytes = byteDashboard.nextN(1, 4);
        this.floatValue = ByteUtils.toFloat(value_bytes);
        super.value = String.valueOf(this.floatValue);
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantFloat(this);
    }
}
