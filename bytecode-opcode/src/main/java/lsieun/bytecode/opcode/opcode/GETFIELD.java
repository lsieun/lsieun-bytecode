package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.FieldInstruction;
import lsieun.bytecode.opcode.facet.StackConsumer;
import lsieun.bytecode.opcode.facet.StackProducer;

public class GETFIELD extends Instruction implements FieldInstruction, StackConsumer, StackProducer {

    public int index;

    public GETFIELD() {
        super(OpcodeConst.GETFIELD, 3);
    }

    public GETFIELD(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitGETFIELD(this);
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
