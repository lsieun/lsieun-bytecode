package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

public class DADD extends Instruction implements ArithmeticInstruction {

    public DADD() {
        super(OpcodeConst.DADD, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDADD(this);
    }

}
