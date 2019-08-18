package lsieun.bytecode.classfile.attrs.classfile;

import lsieun.bytecode.core.cst.AccessConst;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class InnerClass {
    public final int inner_class_info_index;
    public final int outer_class_info_index;
    public final int inner_name_index;
    public final int inner_class_access_flags;

    private final String name;
    private String value;

    public InnerClass(ByteDashboard byteDashboard, ConstantPool constantPool) {
        this.inner_class_info_index = byteDashboard.readUnsignedShort();
        this.outer_class_info_index = byteDashboard.readUnsignedShort();
        this.inner_name_index = byteDashboard.readUnsignedShort();
        this.inner_class_access_flags = byteDashboard.readUnsignedShort();

        this.value = "#" + inner_class_info_index + ",#" + outer_class_info_index
                   + ",#" + inner_name_index + ",#" + AccessConst.getClassAccessFlagsString(inner_class_access_flags);
        if(inner_name_index == 0) {
            // If the Class is anonymous, the value of the 'inner_name_index' item
            // must be zero.
            this.name = "<anonymous class>";
        }
        else {
            this.name = constantPool.getConstantString(inner_name_index, CPConst.CONSTANT_Utf8);
        }

    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
