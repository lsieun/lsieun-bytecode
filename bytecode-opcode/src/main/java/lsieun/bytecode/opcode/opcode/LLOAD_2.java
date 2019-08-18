package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.LoadInstruction;

public final class LLOAD_2 extends Instruction implements LoadInstruction {

    public final int index = 2;

    public LLOAD_2() {
        super(OpcodeConst.LLOAD_2, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitLLOAD_2(this);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        throw new RuntimeException("index is final");
    }

}
