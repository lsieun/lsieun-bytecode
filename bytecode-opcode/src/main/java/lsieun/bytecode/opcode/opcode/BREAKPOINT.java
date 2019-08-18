package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;

/**
 * BREAKPOINT, JVM dependent, ignored by default
 */
public class BREAKPOINT extends Instruction {

    public BREAKPOINT() {
        super(OpcodeConst.BREAKPOINT, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitBREAKPOINT(this);
    }
}
