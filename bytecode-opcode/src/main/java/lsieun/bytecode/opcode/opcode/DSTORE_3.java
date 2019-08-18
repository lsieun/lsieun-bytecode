package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StoreInstruction;

public final class DSTORE_3 extends Instruction implements StoreInstruction {

    public final int index = 3;

    public DSTORE_3() {
        super(OpcodeConst.DSTORE_3, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitDSTORE_3(this);
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
