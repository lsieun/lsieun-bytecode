package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.CPInstruction;
import lsieun.bytecode.opcode.facet.AllocationInstruction;
import lsieun.bytecode.opcode.facet.LoadClass;
import lsieun.bytecode.opcode.facet.StackProducer;

public class NEW extends Instruction
        implements CPInstruction, LoadClass, AllocationInstruction, StackProducer {

    public int index;

    public NEW() {
        super(OpcodeConst.NEW, 3);
    }


    public NEW(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitNEW(this);
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
