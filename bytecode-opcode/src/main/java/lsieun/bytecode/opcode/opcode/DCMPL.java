package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.CompareInstruction;

public class DCMPL extends Instruction implements CompareInstruction {

    public DCMPL() {
        super(OpcodeConst.DCMPL, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDCMPL(this);
    }

}
