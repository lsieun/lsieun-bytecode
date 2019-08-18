package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ArrayInstruction;
import lsieun.bytecode.opcode.facet.StackProducer;

/**
 * LALOAD - Load long from array
 * <PRE>Stack: ..., arrayref, index -&gt; ..., value1, value2</PRE>
 */
public class LALOAD extends Instruction implements ArrayInstruction, StackProducer {

    public LALOAD() {
        super(OpcodeConst.LALOAD, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitLALOAD(this);
    }

}
