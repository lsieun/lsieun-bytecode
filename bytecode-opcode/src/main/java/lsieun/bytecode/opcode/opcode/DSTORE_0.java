package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StoreInstruction;

public final class DSTORE_0 extends Instruction implements StoreInstruction {

    public final int index = 0;

    public DSTORE_0() {
        super(OpcodeConst.DSTORE_0, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitDSTORE_0(this);
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
