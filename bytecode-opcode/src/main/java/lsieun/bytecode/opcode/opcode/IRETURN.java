package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ReturnInstruction;

/**
 * IRETURN -  Return int from d_method
 * <PRE>Stack: ..., value -&gt; &lt;empty&gt;</PRE>
 */
public class IRETURN extends Instruction implements ReturnInstruction {

    public IRETURN() {
        super(OpcodeConst.IRETURN, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIRETURN(this);
    }

}
