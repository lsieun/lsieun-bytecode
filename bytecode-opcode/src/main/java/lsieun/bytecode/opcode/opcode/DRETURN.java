package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ReturnInstruction;

public class DRETURN extends Instruction implements ReturnInstruction {

    public DRETURN() {
        super(OpcodeConst.DRETURN, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDRETURN(this);
    }

}
