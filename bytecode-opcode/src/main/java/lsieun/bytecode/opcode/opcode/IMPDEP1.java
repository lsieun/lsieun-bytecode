package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;

/**
 * IMPDEP1 - Implementation dependent
 */
public class IMPDEP1 extends Instruction {

    public IMPDEP1() {
        super(OpcodeConst.IMPDEP1, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIMPDEP1(this);
    }

}
