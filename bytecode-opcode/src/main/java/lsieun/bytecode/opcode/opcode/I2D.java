package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConversionInstruction;

public class I2D extends Instruction implements ConversionInstruction {

    public I2D() {
        super(OpcodeConst.I2D, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitI2D(this);
    }

}
