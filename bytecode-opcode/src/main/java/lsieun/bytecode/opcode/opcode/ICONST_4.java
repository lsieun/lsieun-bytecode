package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConstantPushInstruction;

public final class ICONST_4 extends Instruction implements ConstantPushInstruction {

    public final int value = 4;

    public ICONST_4() {
        super(OpcodeConst.ICONST_4, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitICONST_4(this);
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

}
