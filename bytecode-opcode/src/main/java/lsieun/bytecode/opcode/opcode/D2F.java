package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.ConversionInstruction;

public class D2F extends Instruction implements ConversionInstruction {

    public D2F() {
        super(OpcodeConst.D2F, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitD2F(this);
    }

}
