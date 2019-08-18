package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConversionInstruction;

/**
 * L2D - Convert long to double
 * <PRE>Stack: ..., value.word1, value.word2 -&gt; ..., result.word1, result.word2</PRE>
 */
public class L2D extends Instruction implements ConversionInstruction {

    public L2D() {
        super(OpcodeConst.L2D, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitL2D(this);
    }

}
