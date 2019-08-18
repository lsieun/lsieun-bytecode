package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.IndexedInstruction;
import lsieun.bytecode.opcode.facet.TypedInstruction;

public class RET extends Instruction implements IndexedInstruction, TypedInstruction {

    public int index; // index to local variable containg the return address

    public RET() {
        super(OpcodeConst.RET, 2);
    }


    public RET(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitRET(this);
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
