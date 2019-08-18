package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.LoadInstruction;

public final class DLOAD_0 extends Instruction implements LoadInstruction {

    public final int index = 0;

    public DLOAD_0() {
        super(OpcodeConst.DLOAD_0, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitDLOAD_0(this);
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
