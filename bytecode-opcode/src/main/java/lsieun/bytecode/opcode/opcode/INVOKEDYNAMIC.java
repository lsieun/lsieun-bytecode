package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.InvokeInstruction;

/**
 * Class for INVOKEDYNAMIC. Not an instance of InvokeInstruction, since that class
 * expects to be able to get the class of the d_method. Ignores the bootstrap
 * mechanism entirely.
 */
public class INVOKEDYNAMIC extends Instruction implements InvokeInstruction {

    public int index;

    public INVOKEDYNAMIC() {
        super(OpcodeConst.INVOKEDYNAMIC, 5);
    }

    public INVOKEDYNAMIC(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitINVOKEDYNAMIC(this);
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
