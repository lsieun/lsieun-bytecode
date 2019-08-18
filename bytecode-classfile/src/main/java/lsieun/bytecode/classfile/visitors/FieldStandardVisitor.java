package lsieun.bytecode.classfile.visitors;

import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.classfile.attrs.field.ConstantValue;
import lsieun.bytecode.classfile.clazz.Attributes;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.classfile.clazz.FieldInfo;
import lsieun.bytecode.cp.utils.ConstantPoolUtils;
import lsieun.bytecode.classfile.utils.FieldUtils;
import lsieun.bytecode.core.cst.AccessConst;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.HexUtils;

@SuppressWarnings("Duplicates")
public class FieldStandardVisitor extends AbstractVisitor {
    private String name_and_type;
    private ConstantPool constant_pool;

    public FieldStandardVisitor(String name_and_type) {
        this.name_and_type = name_and_type;
    }

    @Override
    public void visitClassFile(ClassFile obj) {
        constant_pool = obj.constantPool;
        ConstantPoolUtils.simplify(constant_pool);
        FieldInfo fieldInfo = FieldUtils.findField(obj, name_and_type);
        fieldInfo.accept(this);
    }

    @Override
    public void visitFieldInfo(FieldInfo obj) {
        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);

        String hexCode = obj.getHexCode();
        int access_flags = obj.access_flags;
        int name_index = obj.name_index;
        int descriptor_index = obj.descriptor_index;
        int attributes_count = obj.getAttributesCount();
        Attributes attributes = obj.attributes;

        System.out.println("HexCode:");
        System.out.println(hexCode);
        System.out.println();

        String format = "%s='%s' (%s)";
        String format_with_cp = "%s='%s' (%s) // %s";
        System.out.println(String.format(format, "access_flags", HexUtils.fromBytes(bd.nextN(2)), AccessConst.getMethodAccessFlagsString(access_flags)));
        System.out.println(String.format(format_with_cp, "name_index", HexUtils.fromBytes(bd.nextN(2)), name_index, constant_pool.getConstant(name_index).value));
        System.out.println(String.format(format_with_cp, "descriptor_index", HexUtils.fromBytes(bd.nextN(2)), descriptor_index, constant_pool.getConstant(descriptor_index).value));
        System.out.println(String.format(format, "attributes_count", HexUtils.fromBytes(bd.nextN(2)), attributes_count));

        attributes.accept(this);
    }

    @Override
    public void visitAttributes(Attributes obj) {
        int count = obj.getCount();
        AttributeInfo[] entries = obj.getEntries();

        System.out.println("attributes");
        for (int i = 0; i < count; i++) {
            AttributeInfo item = entries[i];
            if (item == null) continue;
            int attribute_name_index = item.getAttributeNameIndex();
            String name = constant_pool.getConstantString(attribute_name_index, CPConst.CONSTANT_Utf8);
            String line = String.format("\r\n|%03d| %s", (i + 1), name);
            System.out.println(line);
            item.accept(this);
        }
    }

    @Override
    public void visitConstantValue(ConstantValue obj) {
        String hexCode = obj.getHexCode();
        System.out.println(hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();
        int constantvalue_index = obj.getConstantValueIndex();

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));
        System.out.println(String.format(format, "constantvalue_index", HexUtils.fromBytes(bd.nextN(2)), constantvalue_index));
    }
}
