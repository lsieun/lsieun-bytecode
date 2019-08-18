package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArrayInstruction;

public class AALOAD extends Instruction implements ArrayInstruction {

    public AALOAD() {
        super(OpcodeConst.AALOAD, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitAALOAD(this);
    }
}
