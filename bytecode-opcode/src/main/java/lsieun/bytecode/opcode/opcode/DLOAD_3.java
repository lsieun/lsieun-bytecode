package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.LoadInstruction;

public final class DLOAD_3 extends Instruction implements LoadInstruction {

    public final int index = 3;

    public DLOAD_3() {
        super(OpcodeConst.DLOAD_3, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitDLOAD_3(this);
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