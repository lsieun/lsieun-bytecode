package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.InvokeInstruction;

/**
 * INVOKESPECIAL - Invoke instance d_method; special handling for superclass, private
 * and instance initialization d_method invocations
 *
 * <PRE>Stack: ..., objectref, [arg1, [arg2 ...]] -&gt; ...</PRE>
 */
public class INVOKESPECIAL extends Instruction implements InvokeInstruction {

    public int index;

    public INVOKESPECIAL() {
        super(OpcodeConst.INVOKESPECIAL, 3);
    }

    public INVOKESPECIAL(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitINVOKESPECIAL(this);
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
