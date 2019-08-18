package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.CPInstruction;
import lsieun.bytecode.opcode.facet.LoadClass;
import lsieun.bytecode.opcode.facet.StackConsumer;
import lsieun.bytecode.opcode.facet.StackProducer;

public class INSTANCEOF extends Instruction
        implements CPInstruction, LoadClass, StackProducer, StackConsumer {

    public int index;

    public INSTANCEOF() {
        super(OpcodeConst.INSTANCEOF, 3);
    }

    public INSTANCEOF(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitINSTANCEOF(this);
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
