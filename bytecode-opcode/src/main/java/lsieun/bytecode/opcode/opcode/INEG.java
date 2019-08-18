package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

/**
 * INEG - Negate int
 * <PRE>Stack: ..., value -&gt; ..., result</PRE>
 */
public class INEG extends Instruction implements ArithmeticInstruction {

    public INEG() {
        super(OpcodeConst.INEG, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitINEG(this);
    }

}
