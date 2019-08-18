package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StoreInstruction;

public final class LSTORE_0 extends Instruction implements StoreInstruction {

    public final int index = 0;

    public LSTORE_0() {
        super(OpcodeConst.LSTORE_0, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitLSTORE_0(this);
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
