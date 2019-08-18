package lsieun.bytecode.classfile.visitors;

import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.classfile.clazz.Attributes;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.clazz.MethodInfo;
import lsieun.bytecode.classfile.utils.MethodUtils;
import lsieun.bytecode.core.cst.AccessConst;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.HexUtils;

@SuppressWarnings("Duplicates")
public class MethodRawVisitor extends AbstractVisitor {
    private String name_and_type;

    public MethodRawVisitor(String name_and_type) {
        this.name_and_type = name_and_type;
    }

    @Override
    public void visitClassFile(ClassFile obj) {
        MethodInfo methodInfo = MethodUtils.findMethod(obj, name_and_type);
        methodInfo.accept(this);
    }

    @Override
    public void visitMethodInfo(MethodInfo obj) {
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

        String format = "%s (%s)";
        System.out.println("access_flags:");
        System.out.println(String.format(format, HexUtils.fromBytes(bd.nextN(2)), AccessConst.getMethodAccessFlagsString(access_flags)));
        System.out.println();

        System.out.println("name_index:");
        System.out.println(String.format(format, HexUtils.fromBytes(bd.nextN(2)), name_index));
        System.out.println();

        System.out.println("descriptor_index:");
        System.out.println(String.format(format, HexUtils.fromBytes(bd.nextN(2)), descriptor_index));
        System.out.println();

        System.out.println("attributes_count:");
        System.out.println(String.format(format, HexUtils.fromBytes(bd.nextN(2)), attributes_count));
        System.out.println();

        attributes.accept(this);
    }

    @Override
    public void visitAttributes(Attributes obj) {
        System.out.println("attributes");
        for (int i = 0; i < obj.getCount(); i++) {
            AttributeInfo item = obj.getEntries()[i];
            String line = String.format("|%03d| %s", (i + 1), item.getHexCode());
            System.out.println(line);
        }
        System.out.println();
    }
}
