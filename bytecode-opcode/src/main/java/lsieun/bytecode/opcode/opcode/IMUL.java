package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

/**
 * IMUL - Multiply ints
 * <PRE>Stack: ..., value1, value2 -&gt; result</PRE>
 */
public class IMUL extends Instruction implements ArithmeticInstruction {

    public IMUL() {
        super(OpcodeConst.IMUL,1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIMUL(this);
    }

}
