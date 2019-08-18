package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConversionInstruction;

public class D2L extends Instruction implements ConversionInstruction {

    public D2L() {
        super(OpcodeConst.D2L, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitD2L(this);
    }

}