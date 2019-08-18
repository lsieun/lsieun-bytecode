package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.UnconditionalBranch;

public class ATHROW extends Instruction implements UnconditionalBranch {

    public ATHROW() {
        super(OpcodeConst.ATHROW, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitATHROW(this);
    }
}
