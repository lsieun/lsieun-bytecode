package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConstantPushInstruction;

public final class ICONST_1 extends Instruction implements ConstantPushInstruction {

    public final int value = 1;

    public ICONST_1() {
        super(OpcodeConst.ICONST_1, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitICONST_1(this);
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

}
