package lsieun.bytecode.opcode.opcode;


import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConversionInstruction;

/**
 * L2I - Convert long to int
 * <PRE>Stack: ..., value.word1, value.word2 -&gt; ..., result</PRE>
 */
public class L2I extends Instruction implements ConversionInstruction {

    public L2I() {
        super(OpcodeConst.L2I, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitL2I(this);
    }

}
