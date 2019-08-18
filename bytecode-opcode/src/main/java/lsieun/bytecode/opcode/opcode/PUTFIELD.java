package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.FieldInstruction;
import lsieun.bytecode.opcode.facet.PopInstruction;

public class PUTFIELD extends Instruction implements FieldInstruction, PopInstruction {

    public int index;

    public PUTFIELD() {
        super(OpcodeConst.PUTFIELD, 3);
    }

    public PUTFIELD(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitPUTFIELD(this);
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
