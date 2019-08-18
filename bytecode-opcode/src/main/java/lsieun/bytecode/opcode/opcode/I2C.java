package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConversionInstruction;

public class I2C extends Instruction implements ConversionInstruction {

    public I2C() {
        super(OpcodeConst.I2C, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitI2C(this);
    }

}
