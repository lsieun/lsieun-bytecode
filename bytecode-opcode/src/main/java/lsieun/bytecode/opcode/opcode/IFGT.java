package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.IfInstruction;

public class IFGT extends Instruction implements IfInstruction {

    public int branch;

    public IFGT() {
        super(OpcodeConst.IFGT, 3);
    }

    public IFGT(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIFGT(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
