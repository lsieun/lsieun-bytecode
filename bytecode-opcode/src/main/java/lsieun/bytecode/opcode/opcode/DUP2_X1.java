package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StackInstruction;

public class DUP2_X1 extends Instruction implements StackInstruction {

    public DUP2_X1() {
        super(OpcodeConst.DUP2_X1, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDUP2_X1(this);
    }

}
