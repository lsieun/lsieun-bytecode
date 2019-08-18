package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StoreInstruction;

/**
 * ISTORE - Store int from f_stack into local variable
 * <PRE>Stack: ..., value -&gt; ... </PRE>
 */
public class ISTORE extends Instruction implements StoreInstruction {

    public int index;

    public ISTORE() {
        super(OpcodeConst.ISTORE, 2);
    }

    public ISTORE(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitISTORE(this);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

}
