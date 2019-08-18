package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArrayInstruction;
import lsieun.bytecode.opcode.facet.StackConsumer;

/**
 * LASTORE -  Store into long array
 * <PRE>Stack: ..., arrayref, index, value.word1, value.word2 -&gt; ...</PRE>
 */
public class LASTORE extends Instruction implements ArrayInstruction, StackConsumer {

    public LASTORE() {
        super(OpcodeConst.LASTORE, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitLASTORE(this);
    }

}
