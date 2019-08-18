package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

public class DNEG extends Instruction implements ArithmeticInstruction {

    public DNEG() {
        super(OpcodeConst.DNEG, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitDNEG(this);
    }

}
