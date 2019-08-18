package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConversionInstruction;

public class F2I extends Instruction implements ConversionInstruction {

    public F2I() {
        super(OpcodeConst.F2I, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitF2I(this);
    }

}
