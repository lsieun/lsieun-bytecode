package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConstantPushInstruction;

public final class ICONST_3 extends Instruction implements ConstantPushInstruction {

    public final int value = 3;

    public ICONST_3() {
        super(OpcodeConst.ICONST_3, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitICONST_3(this);
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

}
