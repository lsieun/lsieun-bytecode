package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StackInstruction;
import lsieun.bytecode.opcode.facet.PushInstruction;

public class DUP2 extends Instruction implements StackInstruction, PushInstruction {

    public DUP2() {
        super(OpcodeConst.DUP2, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDUP2(this);
    }

}
