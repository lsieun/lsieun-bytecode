package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.AllocationInstruction;
import lsieun.bytecode.opcode.facet.StackProducer;

public class NEWARRAY extends Instruction
        implements AllocationInstruction, StackProducer {

    public byte atype;

    public NEWARRAY() {
        super(OpcodeConst.NEWARRAY, 2);
    }

    public NEWARRAY(final byte atype) {
        this();
        this.atype = atype;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitNEWARRAY(this);
    }

}
