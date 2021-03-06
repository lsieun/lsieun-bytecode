package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.StoreInstruction;

public class DSTORE extends Instruction implements StoreInstruction {

    public int index;

    public DSTORE() {
        super(OpcodeConst.DSTORE, 2);
    }

    public DSTORE(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDSTORE(this);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

}
