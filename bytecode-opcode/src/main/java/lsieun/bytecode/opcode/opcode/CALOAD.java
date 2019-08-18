package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArrayInstruction;
import lsieun.bytecode.opcode.facet.StackProducer;

public class CALOAD extends Instruction implements ArrayInstruction, StackProducer {

    public CALOAD() {
        super(OpcodeConst.CALOAD, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitCALOAD(this);
    }

}
