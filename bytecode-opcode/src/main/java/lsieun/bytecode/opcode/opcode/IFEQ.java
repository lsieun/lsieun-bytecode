package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.IfInstruction;

public class IFEQ extends Instruction implements IfInstruction {

    public int branch;

    public IFEQ() {
        super(OpcodeConst.IFEQ, 3);
    }

    public IFEQ(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIFEQ(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
