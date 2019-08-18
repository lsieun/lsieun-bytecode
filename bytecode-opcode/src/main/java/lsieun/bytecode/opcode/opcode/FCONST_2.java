package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConstantPushInstruction;

public final class FCONST_2 extends Instruction implements ConstantPushInstruction {

    public final float value = 2;

    public FCONST_2() {
        super(OpcodeConst.FCONST_2, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitFCONST_2(this);
    }

    @Override
    public Number getValue() {
        return Float.valueOf(value);
    }

}
