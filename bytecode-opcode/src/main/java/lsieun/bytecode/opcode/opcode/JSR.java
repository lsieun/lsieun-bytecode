package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.JsrInstruction;
import lsieun.bytecode.opcode.facet.VariableLengthInstruction;

/**
 * JSR - Jump to subroutine
 */
public class JSR extends Instruction implements JsrInstruction, VariableLengthInstruction {

    public int branch;

    public JSR() {
        super(OpcodeConst.JSR, 3);
    }

    public JSR(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitJSR(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
