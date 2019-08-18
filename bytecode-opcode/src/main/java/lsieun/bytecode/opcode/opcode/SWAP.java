package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StackConsumer;
import lsieun.bytecode.opcode.facet.StackInstruction;
import lsieun.bytecode.opcode.facet.StackProducer;

public class SWAP extends Instruction implements StackInstruction, StackConsumer, StackProducer {

    public SWAP() {
        super(OpcodeConst.SWAP, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitSWAP(this);
    }

}
