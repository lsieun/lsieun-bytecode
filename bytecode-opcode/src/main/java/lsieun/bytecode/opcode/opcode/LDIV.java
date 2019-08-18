package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

public class LDIV extends Instruction implements ArithmeticInstruction {

    public LDIV() {
        super(OpcodeConst.LDIV, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitLDIV(this);
    }

}
