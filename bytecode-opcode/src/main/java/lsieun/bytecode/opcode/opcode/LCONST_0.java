package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConstantPushInstruction;

public final class LCONST_0 extends Instruction implements ConstantPushInstruction {

    public final long value = 0;

    public LCONST_0() {
        super(OpcodeConst.LCONST_0, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitLCONST_0(this);
    }

    @Override
    public Number getValue() {
        return Long.valueOf(value);
    }

}
