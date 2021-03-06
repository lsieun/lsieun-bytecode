package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;

/**
 * IMPDEP2 - Implementation dependent
 */
public class IMPDEP2 extends Instruction {

    public IMPDEP2() {
        super(OpcodeConst.IMPDEP2, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIMPDEP2(this);
    }

}
