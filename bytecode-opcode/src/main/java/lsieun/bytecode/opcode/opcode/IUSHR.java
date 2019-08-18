package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

/**
 * IUSHR - Logical shift right int
 * <PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>
 */
public class IUSHR extends Instruction implements ArithmeticInstruction {

    public IUSHR() {
        super(OpcodeConst.IUSHR, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIUSHR(this);
    }

}
