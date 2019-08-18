package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.PopInstruction;
import lsieun.bytecode.opcode.facet.StackInstruction;

public class POP2 extends Instruction implements StackInstruction, PopInstruction {

    public POP2() {
        super(OpcodeConst.POP2, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitPOP2(this);
    }

}
