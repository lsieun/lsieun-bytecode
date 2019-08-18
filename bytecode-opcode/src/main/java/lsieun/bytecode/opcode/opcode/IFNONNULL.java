package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.IfInstruction;

public class IFNONNULL extends Instruction implements IfInstruction {

    public int branch;

    public IFNONNULL() {
        super(OpcodeConst.IFNONNULL, 3);
    }

    public IFNONNULL(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIFNONNULL(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
