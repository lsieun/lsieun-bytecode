package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

public class DMUL extends Instruction implements ArithmeticInstruction {

    public DMUL() {
        super(OpcodeConst.DMUL, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDMUL(this);
    }

}
