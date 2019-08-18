package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

/**
 * ISUB - Substract ints
 * <PRE>Stack: ..., value1, value2 -&gt; result</PRE>
 */
public class ISUB extends Instruction implements ArithmeticInstruction {

    public ISUB() {
        super(OpcodeConst.ISUB, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitISUB(this);
    }

}
