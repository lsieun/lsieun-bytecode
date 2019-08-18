package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.IfInstruction;

public class IF_ACMPEQ extends Instruction implements IfInstruction {

    public int branch;

    public IF_ACMPEQ() {
        super(OpcodeConst.IF_ACMPEQ, 3);
    }

    public IF_ACMPEQ(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIF_ACMPEQ(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
