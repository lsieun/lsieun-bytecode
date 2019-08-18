package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

public class IDIV extends Instruction implements ArithmeticInstruction {

    public IDIV() {
        super(OpcodeConst.IDIV, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIDIV(this);
    }

}
