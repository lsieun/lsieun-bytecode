package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StackConsumer;

public class MONITOREXIT extends Instruction implements StackConsumer {

    public MONITOREXIT() {
        super(OpcodeConst.MONITOREXIT, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitMONITOREXIT(this);
    }

}
