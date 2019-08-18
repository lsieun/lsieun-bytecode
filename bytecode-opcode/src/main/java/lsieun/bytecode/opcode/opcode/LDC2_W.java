package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.CPInstruction;
import lsieun.bytecode.opcode.facet.PushInstruction;

public class LDC2_W extends Instruction implements CPInstruction, PushInstruction {

    public int  index;

    public LDC2_W() {
        super(OpcodeConst.LDC2_W, 3);
    }

    public LDC2_W(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitLDC2_W(this);
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
