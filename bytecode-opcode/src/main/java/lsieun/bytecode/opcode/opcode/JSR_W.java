package lsieun.bytecode.opcode.opcode;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.opcode.Instruction;
import lsieun.bytecode.opcode.OpcodeVisitor;
import lsieun.bytecode.opcode.facet.JsrInstruction;

/**
 * JSR_W - Jump to subroutine
 */
public class JSR_W extends Instruction implements JsrInstruction {

    public int branch;

    public JSR_W() {
        super(OpcodeConst.JSR_W, 5);
    }

    public JSR_W(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitJSR_W(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
