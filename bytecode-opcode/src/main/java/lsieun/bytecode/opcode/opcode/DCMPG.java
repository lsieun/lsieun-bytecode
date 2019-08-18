package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.CompareInstruction;

public class DCMPG extends Instruction implements CompareInstruction {

    public DCMPG() {
        super(OpcodeConst.DCMPG, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDCMPG(this);
    }

}
