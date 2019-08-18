package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.TypedInstruction;

public class ACONST_NULL extends Instruction implements TypedInstruction {

    public ACONST_NULL() {
        super(OpcodeConst.ACONST_NULL, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitACONST_NULL(this);
    }

}
