package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.IfInstruction;

public class IFNE extends Instruction implements IfInstruction {

    public int branch;

    public IFNE() {
        super(OpcodeConst.IFNE, 3);
    }

    public IFNE(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIFNE(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
