package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.CPInstruction;
import lsieun.bytecode.opcode.facet.AllocationInstruction;
import lsieun.bytecode.opcode.facet.LoadClass;
import lsieun.bytecode.opcode.facet.StackConsumer;
import lsieun.bytecode.opcode.facet.StackProducer;

public class ANEWARRAY extends Instruction
        implements CPInstruction, LoadClass, AllocationInstruction, StackConsumer, StackProducer {

    public int index;

    public ANEWARRAY() {
        super(OpcodeConst.ANEWARRAY, 3);
    }

    public ANEWARRAY(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitANEWARRAY(this);
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
