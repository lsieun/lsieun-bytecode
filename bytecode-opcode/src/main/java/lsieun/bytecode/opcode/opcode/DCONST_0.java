package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConstantPushInstruction;

public final class DCONST_0 extends Instruction implements ConstantPushInstruction {

    public double value = 0;

    public DCONST_0() {
        super(OpcodeConst.DCONST_0, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitDCONST_0(this);
    }

    @Override
    public Number getValue() {
        return Double.valueOf(value);
    }

}
