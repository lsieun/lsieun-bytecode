package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConstantPushInstruction;

public class SIPUSH extends Instruction implements ConstantPushInstruction {

    public short value;

    public SIPUSH() {
        super(OpcodeConst.SIPUSH, 3);
    }

    public SIPUSH(final short value) {
        this();
        this.value = value;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitSIPUSH(this);
    }

}
