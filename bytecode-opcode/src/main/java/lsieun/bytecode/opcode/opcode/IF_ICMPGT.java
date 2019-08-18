package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.IfInstruction;

public class IF_ICMPGT extends Instruction implements IfInstruction {

    public int branch;

    public IF_ICMPGT() {
        super(OpcodeConst.IF_ICMPGT, 3);
    }

    public IF_ICMPGT(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIF_ICMPGT(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
