package lsieun.bytecode.opcode.opcode;


import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConversionInstruction;

/**
 * L2F - Convert long to float
 * <PRE>Stack: ..., value.word1, value.word2 -&gt; ..., result</PRE>
 */
public class L2F extends Instruction implements ConversionInstruction {

    public L2F() {
        super(OpcodeConst.L2F, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitL2F(this);
    }

}
