package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;

/**
 * NOP - Do nothing
 */
public class NOP extends Instruction {

    public NOP() {
        super(OpcodeConst.NOP, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitNOP(this);
    }

}
