package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArithmeticInstruction;

/**
 * LADD - Add longs
 * <PRE>Stack: ..., value1.word1, value1.word2, value2.word1, value2.word2 -&gt;</PRE>
 * ..., result.word1, result.word2
 */
public class LADD extends Instruction implements ArithmeticInstruction {

    public LADD() {
        super(OpcodeConst.LADD, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitLADD(this);
    }

}
