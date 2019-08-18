package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StackConsumer;
import lsieun.bytecode.opcode.facet.StackProducer;

public class ARRAYLENGTH extends Instruction implements StackProducer, StackConsumer {

    public ARRAYLENGTH() {
        super(OpcodeConst.ARRAYLENGTH, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitARRAYLENGTH(this);
    }
}
