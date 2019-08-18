package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StoreInstruction;

public final class FSTORE_1 extends Instruction implements StoreInstruction {

    public final int index = 1;

    public FSTORE_1() {
        super(OpcodeConst.FSTORE_1, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitFSTORE_1(this);
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
