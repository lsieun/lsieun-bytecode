package lsieun.bytecode.classfile.visitors;

import java.util.Arrays;

import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.classfile.clazz.*;
import lsieun.bytecode.classfile.utils.AttributeUtils;
import lsieun.bytecode.core.CompilerVersion;
import lsieun.bytecode.core.MagicNumber;
import lsieun.bytecode.core.cst.AccessConst;
import lsieun.bytecode.core.visitor.CoreStandardVisitor;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.cp.visitor.CPStandardVisitor;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.HexUtils;

public class ClassFileStandardVisitor extends AbstractVisitor {
    @Override
    public void visitClassFile(ClassFile obj) {
        CoreStandardVisitor coreStandardVisitor = new CoreStandardVisitor();
        CPStandardVisitor cpStandardVisitor = new CPStandardVisitor();

        MagicNumber magicNumber = obj.magicNumber;
        magicNumber.accept(coreStandardVisitor);

        CompilerVersion compilerVersion = obj.compilerVersion;
        compilerVersion.accept(coreStandardVisitor);

        ConstantPool constantPool = obj.constantPool;
        constantPool.accept(cpStandardVisitor);

        ClassInfo classInfo = obj.classInfo;
        classInfo.accept(this);

        Fields fields = obj.fields;
        fields.accept(this);

        Methods methods = obj.methods;
        methods.accept(this);

        Attributes attributes = obj.attributes;
        attributes.accept(this);
    }


    @Override
    public void visitClassInfo(ClassInfo obj) {
        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        String hexCode = obj.getHexCode();
        int access_flags = obj.access_flags;
        int this_class = obj.this_class;
        int super_class = obj.super_class;
        int interfaces_count = obj.interfaces_count;
        int[] interfaces = obj.interfaces;
        String class_info_line = String.format("class_info='%s'", hexCode);
        System.out.println(class_info_line);

        String format = "    %s='%s' (%s)";
        System.out.println(String.format(format, "access_flags", HexUtils.fromBytes(bd.nextN(2)), AccessConst.getClassAccessFlagsString(access_flags)));
        System.out.println(String.format(format, "this_class", HexUtils.fromBytes(bd.nextN(2)), this_class));
        System.out.println(String.format(format, "super_class", HexUtils.fromBytes(bd.nextN(2)), super_class));
        System.out.println(String.format(format, "interfaces_count", HexUtils.fromBytes(bd.nextN(2)), interfaces_count));
        System.out.println(String.format(format, "interfaces", HexUtils.fromBytes(bd.nextN(2*interfaces_count)), Arrays.toString(interfaces)));
    }

    @Override
    public void visitFields(Fields obj) {
        int count = obj.fields_count;
        FieldInfo[] entries = obj.entries;

        String countLine = String.format("fields_count='%s' (%d)", obj.getHexCode(), count);
        System.out.println(countLine);

        System.out.println("fields");
        for(FieldInfo item : entries) {
            if(item == null) continue;
            item.accept(this);
        }
    }

    @Override
    public void visitFieldInfo(FieldInfo obj) {
        Attributes attributes = obj.attributes;
        String attrNames = AttributeUtils.getAttributeNames(attributes);

        String line = String.format("    FieldInfo {Value='%s', AccessFlags='%s', Attrs='%s', HexCode='%s'}",
                obj.getValue(),
                obj.getAccessFlagsString(),
                attrNames,
                obj.getHexCode());
        System.out.println(line);
    }

    @Override
    public void visitMethods(Methods obj) {
        int count = obj.methods_count;
        MethodInfo[] entries = obj.entries;

        String countLine = String.format("methods_count='%s' (%d)", obj.getHexCode(), count);
        System.out.println(countLine);

        System.out.println("methods");
        for(MethodInfo item : entries) {
            if(item == null) continue;
            item.accept(this);
        }
    }

    @Override
    public void visitMethodInfo(MethodInfo obj) {
        Attributes attributes = obj.attributes;
        String attrNames = AttributeUtils.getAttributeNames(attributes);

        String line = String.format("    MethodInfo {Value='%s', AccessFlags='%s', Attrs='%s', HexCode='%s'}",
                obj.getValue(),
                obj.getAccessFlagsString(),
                attrNames,
                "..." /*+ obj.getHexCode()*/);
        System.out.println(line);
    }

    @Override
    public void visitAttributes(Attributes obj) {
        int count = obj.getCount();
        AttributeInfo[] entries = obj.getEntries();

        String countLine = String.format("attributes_count='%s' (%d)", obj.getHexCode(), count);
        System.out.println(countLine);

        System.out.println("attributes");
        for(AttributeInfo item : entries) {
            if(item == null) continue;
            item.accept(this);
        }
    }

    @Override
    public void visitAttributeInfo(AttributeInfo obj) {
        byte[] bytes = obj.getBytes();
        ByteDashboard byteDashboard = new ByteDashboard(bytes);
        byte[] attribute_name_index_bytes = byteDashboard.nextN(2);
        byte[] attribute_length_bytes = byteDashboard.nextN(4);


        String line = String.format("    %s {name_index='%s'(%d), length='%s'(%d), HexCode='%s'}",
                obj.getName(),
                HexUtils.fromBytes(attribute_name_index_bytes), obj.getAttributeNameIndex(),
                HexUtils.fromBytes(attribute_length_bytes), obj.getAttributeLength(),
                obj.getHexCode());
        System.out.println(line);
    }
}
