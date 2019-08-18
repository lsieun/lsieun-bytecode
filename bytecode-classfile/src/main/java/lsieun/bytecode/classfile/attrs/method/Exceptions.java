package lsieun.bytecode.classfile.attrs.method;

import java.util.ArrayList;
import java.util.List;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;
import lsieun.utils.StringUtils;

public final class Exceptions extends AttributeInfo {
    private final int number_of_exceptions;
    private final int[] exception_index_array;
    private final String value;

    public Exceptions(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);

        this.number_of_exceptions = byteDashboard.readUnsignedShort();
        this.exception_index_array = new int[number_of_exceptions];

        List<String> list = new ArrayList();
        for (int i = 0; i < number_of_exceptions; i++) {
            int exception_index = byteDashboard.readUnsignedShort();
            this.exception_index_array[i] = exception_index;

            String value = constantPool.getConstantString(exception_index, CPConst.CONSTANT_Class);
            list.add(value);
        }
        this.value = StringUtils.list2str(list, "[", "]", ", ");
    }

    public int getNumberOfExceptions() {
        return number_of_exceptions;
    }

    public int[] getExceptionIndexArray() {
        return exception_index_array;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void accept(Visitor v) {
        v.visitExceptions(this);
    }

}
