package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.IfInstruction;

public class IFGE extends Instruction implements IfInstruction {

    public int branch;

    public IFGE() {
        super(OpcodeConst.IFGE, 3);
    }

    public IFGE(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIFGE(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
