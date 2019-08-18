package lsieun.bytecode.cp;

import java.nio.charset.StandardCharsets;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;

public final class ConstantUtf8 extends Constant {
    public final int length;
    public final String utf8Value;

    ConstantUtf8(ByteDashboard byteDashboard) {
        super(CPConst.CONSTANT_Utf8);
        this.length = byteDashboard.peekInt(1, 2);

        int total = 3 + length;
        byte[] bytes = byteDashboard.peekN(total);
        super.setBytes(bytes);

        byte[] utf8_bytes = byteDashboard.nextN(3, length);
        this.utf8Value = new String(utf8_bytes, StandardCharsets.UTF_8);
        super.value = utf8Value;
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantUtf8(this);
    }

}
