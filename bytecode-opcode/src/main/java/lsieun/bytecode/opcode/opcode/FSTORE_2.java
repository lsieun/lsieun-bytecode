package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StoreInstruction;

public final class FSTORE_2 extends Instruction implements StoreInstruction {

    public final int index = 2;

    public FSTORE_2() {
        super(OpcodeConst.FSTORE_2, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitFSTORE_2(this);
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
