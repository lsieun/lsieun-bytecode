package lsieun.bytecode.cp;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;

public final class ConstantInvokeDynamic extends Constant {
    public final int bootstrap_method_attr_index;
    public final int name_and_type_index;

    public ConstantInvokeDynamic(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_InvokeDynamic);
        byte[] bytes = byteDashboard.peekN(5);
        super.setBytes(bytes);

        byteDashboard.skip(1);
        this.bootstrap_method_attr_index = byteDashboard.readUnsignedShort();
        this.name_and_type_index = byteDashboard.readUnsignedShort();
        super.value = "#" + name_and_type_index;
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantInvokeDynamic(this);
    }
}
