package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.IfInstruction;

public class IFLE extends Instruction implements IfInstruction {

    public int branch;

    public IFLE() {
        super(OpcodeConst.IFLE, 3);
    }

    public IFLE(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIFLE(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
