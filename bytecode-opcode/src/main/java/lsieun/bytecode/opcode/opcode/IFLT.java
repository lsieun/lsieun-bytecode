package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.IfInstruction;

public class IFLT extends Instruction implements IfInstruction {

    public int branch;

    public IFLT() {
        super(OpcodeConst.IFLT, 3);
    }

    public IFLT(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIFLT(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
