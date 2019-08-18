package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

/**
 * ISHL - Arithmetic shift left int
 * <PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>
 */
public class ISHL extends Instruction implements ArithmeticInstruction {

    public ISHL() {
        super(OpcodeConst.ISHL, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitISHL(this);
    }

}
