package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.InvokeInstruction;

/**
 * INVOKEINTERFACE - Invoke interface d_method
 * <PRE>Stack: ..., objectref, [arg1, [arg2 ...]] -&gt; ...</PRE>
 */
public final class INVOKEINTERFACE extends Instruction implements InvokeInstruction {

    public int index;
    public int count;

    public INVOKEINTERFACE() {
        super(OpcodeConst.INVOKEINTERFACE, 5);
    }

    public INVOKEINTERFACE(final int index, final int count) {
        this();
        this.index = index;
        this.count = count;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitINVOKEINTERFACE(this);
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
