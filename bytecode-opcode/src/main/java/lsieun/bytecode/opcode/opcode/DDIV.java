package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

public class DDIV extends Instruction implements ArithmeticInstruction {

    public DDIV() {
        super(OpcodeConst.DDIV, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitDDIV(this);
    }

}
