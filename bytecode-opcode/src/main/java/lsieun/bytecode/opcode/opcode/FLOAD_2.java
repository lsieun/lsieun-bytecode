package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.LoadInstruction;

public final class FLOAD_2 extends Instruction implements LoadInstruction {

    public final int index = 2;

    public FLOAD_2() {
        super(OpcodeConst.FLOAD_2, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitFLOAD_2(this);
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
