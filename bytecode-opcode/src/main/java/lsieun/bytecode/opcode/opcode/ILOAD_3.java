package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.LoadInstruction;

public final class ILOAD_3 extends Instruction implements LoadInstruction {

    public final int index = 3;

    public ILOAD_3() {
        super(OpcodeConst.ILOAD_3, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitILOAD_3(this);
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
