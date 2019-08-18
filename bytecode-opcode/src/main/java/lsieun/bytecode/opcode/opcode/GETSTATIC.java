package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.FieldInstruction;
import lsieun.bytecode.opcode.facet.PushInstruction;

public class GETSTATIC extends Instruction implements FieldInstruction, PushInstruction {

    public int index;

    public GETSTATIC() {
        super(OpcodeConst.GETSTATIC, 3);
    }

    public GETSTATIC(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitGETSTATIC(this);
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
