package lsieun.bytecode.opcode.opcode;


import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConversionInstruction;

public class I2F extends Instruction implements ConversionInstruction {

    public I2F() {
        super(OpcodeConst.I2F, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitI2F(this);
    }

}
