package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArrayInstruction;
import lsieun.bytecode.opcode.facet.StackConsumer;

public class IASTORE extends Instruction implements ArrayInstruction, StackConsumer {

    public IASTORE() {
        super(OpcodeConst.IASTORE, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIASTORE(this);
    }

}
