package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.GotoInstruction;

public class GOTO extends Instruction implements GotoInstruction {

    public int branch;

    public GOTO() {
        super(OpcodeConst.GOTO, 3);
    }

    public GOTO(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitGOTO(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }
}
