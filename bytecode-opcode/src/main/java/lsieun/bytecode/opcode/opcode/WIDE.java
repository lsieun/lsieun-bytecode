package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;

public class WIDE extends Instruction {

    public WIDE() {
        super(OpcodeConst.WIDE, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitWIDE(this);
    }

}
