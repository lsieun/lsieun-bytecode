package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.IfInstruction;

public class IF_ICMPGE extends Instruction implements IfInstruction {

    public int branch;

    public IF_ICMPGE() {
        super(OpcodeConst.IF_ICMPGE, 3);
    }

    public IF_ICMPGE(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIF_ICMPGE(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
