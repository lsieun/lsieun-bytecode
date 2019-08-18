package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.FieldInstruction;
import lsieun.bytecode.opcode.facet.PopInstruction;

public class PUTSTATIC extends Instruction implements FieldInstruction, PopInstruction {

    public int index;

    public PUTSTATIC() {
        super(OpcodeConst.PUTSTATIC, 3);
    }

    public PUTSTATIC(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitPUTSTATIC(this);
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
