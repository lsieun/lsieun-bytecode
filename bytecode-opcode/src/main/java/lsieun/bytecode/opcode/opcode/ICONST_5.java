package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConstantPushInstruction;

public final class ICONST_5 extends Instruction implements ConstantPushInstruction {

    public final int value = 5;

    public ICONST_5() {
        super(OpcodeConst.ICONST_5, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitICONST_5(this);
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

}
