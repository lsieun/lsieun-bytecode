package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StackInstruction;
import lsieun.bytecode.opcode.facet.PushInstruction;

public class DUP extends Instruction implements StackInstruction, PushInstruction {

    public DUP() {
        super(OpcodeConst.DUP, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDUP(this);
    }

}
