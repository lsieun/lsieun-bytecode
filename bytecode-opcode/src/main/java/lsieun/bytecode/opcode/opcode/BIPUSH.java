package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConstantPushInstruction;

public class BIPUSH extends Instruction implements ConstantPushInstruction {
    public byte value;

    public BIPUSH() {
        super(OpcodeConst.BIPUSH, 2);
    }

    public BIPUSH(final byte value) {
        this();
        this.value = value;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitBIPUSH(this);
    }

}
