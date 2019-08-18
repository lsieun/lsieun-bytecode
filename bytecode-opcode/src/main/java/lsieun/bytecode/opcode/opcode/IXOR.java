package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

/**
 * IXOR - Bitwise XOR int
 * <PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>
 */
public class IXOR extends Instruction implements ArithmeticInstruction {

    public IXOR() {
        super(OpcodeConst.IXOR, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIXOR(this);
    }

}

