package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.IfInstruction;

public class IFNULL extends Instruction implements IfInstruction {

    public int branch;

    public IFNULL() {
        super(OpcodeConst.IFNULL, 3);
    }

    public IFNULL(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIFNULL(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
