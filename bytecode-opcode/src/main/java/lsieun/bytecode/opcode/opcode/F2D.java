package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConversionInstruction;

public class F2D extends Instruction implements ConversionInstruction {

    public F2D() {
        super(OpcodeConst.F2D, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitF2D(this);
    }

}
