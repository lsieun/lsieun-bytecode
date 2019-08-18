package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.InvokeInstruction;

/**
 * INVOKESTATIC - Invoke a class (static) d_method
 *
 * <PRE>Stack: ..., [arg1, [arg2 ...]] -&gt; ...</PRE>
 */
public class INVOKESTATIC extends Instruction implements InvokeInstruction {

    public int index;

    public INVOKESTATIC() {
        super(OpcodeConst.INVOKESTATIC, 3);
    }

    public INVOKESTATIC(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitINVOKESTATIC(this);
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
