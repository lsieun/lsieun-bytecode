package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConversionInstruction;

public class I2S extends Instruction implements ConversionInstruction {

    public I2S() {
        super(OpcodeConst.I2S, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitI2S(this);
    }

}
