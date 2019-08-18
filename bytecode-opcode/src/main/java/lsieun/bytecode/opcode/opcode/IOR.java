package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

/**
 * IOR - Bitwise OR int
 * <PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>
 */
public class IOR extends Instruction implements ArithmeticInstruction {

    public IOR() {
        super(OpcodeConst.IOR, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIOR(this);
    }

}
