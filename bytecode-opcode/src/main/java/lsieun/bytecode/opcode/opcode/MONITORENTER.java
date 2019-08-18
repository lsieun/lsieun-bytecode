package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StackConsumer;

public class MONITORENTER extends Instruction implements StackConsumer {

    public MONITORENTER() {
        super(OpcodeConst.MONITORENTER, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitMONITORENTER(this);
    }

}
