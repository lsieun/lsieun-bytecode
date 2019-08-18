package lsieun.bytecode.classfile.attrs.method;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class ExceptionTable {
    public final int start_pc;
    public final int end_pc;
    public final int handler_pc;
    public final int catch_type;
    public String exceptionType;

    public ExceptionTable(ByteDashboard byteDashboard, ConstantPool constantPool) {
        this.start_pc = byteDashboard.readUnsignedShort();
        this.end_pc = byteDashboard.readUnsignedShort();
        this.handler_pc = byteDashboard.readUnsignedShort();
        this.catch_type = byteDashboard.readUnsignedShort();

        String exceptionType = "";
        if(catch_type == 0) {
            // If the value of the 'catch_type' item is zero, this exception handler is called
            // for all exceptions.
            exceptionType = "All";
        }
        else {
            exceptionType = constantPool.getConstant(catch_type, CPConst.CONSTANT_Class).value;
        }

        //exceptionType = exceptionType.replaceAll("/", ".");
        this.exceptionType = exceptionType;
    }

}
