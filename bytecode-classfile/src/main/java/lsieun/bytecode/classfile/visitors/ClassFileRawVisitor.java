package lsieun.bytecode.classfile.visitors;

import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.classfile.clazz.*;
import lsieun.bytecode.core.visitor.CoreRawVisitor;
import lsieun.bytecode.cp.visitor.CPRawVisitor;

public class ClassFileRawVisitor extends AbstractVisitor {
    private static final CoreRawVisitor coreRawVisitor = new CoreRawVisitor();
    private static final CPRawVisitor cpRawVisitor = new CPRawVisitor();

    @Override
    public void visitClassFile(ClassFile obj) {
        obj.magicNumber.accept(coreRawVisitor);
        obj.compilerVersion.accept(coreRawVisitor);
        obj.constantPool.accept(cpRawVisitor);
        obj.classInfo.accept(this);
        obj.fields.accept(this);
        obj.methods.accept(this);
        obj.attributes.accept(this);
    }

    @Override
    public void visitClassInfo(ClassInfo obj) {
        System.out.println("class_info");
        System.out.println(obj.getHexCode());
        System.out.println();
    }

    @Override
    public void visitFields(Fields obj) {
        System.out.println("fields_count");
        System.out.println(obj.getHexCode());
        System.out.println();

        System.out.println("fields");
        for (int i = 0; i < obj.fields_count; i++) {
            FieldInfo item = obj.entries[i];
            String line = String.format("|%03d| %s", (i + 1), item.getHexCode());
            System.out.println(line);
        }
        System.out.println();
    }

    @Override
    public void visitMethods(Methods obj) {
        System.out.println("methods_count");
        System.out.println(obj.getHexCode());
        System.out.println();

        System.out.println("methods");
        for (int i = 0; i < obj.methods_count; i++) {
            MethodInfo item = obj.entries[i];
            String line = String.format("|%03d| %s", (i + 1), item.getHexCode());
            System.out.println(line);
        }
        System.out.println();
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void visitAttributes(Attributes obj) {
        System.out.println("attributes_count");
        System.out.println(obj.getHexCode());
        System.out.println();

        System.out.println("attributes");
        for (int i = 0; i < obj.getCount(); i++) {
            AttributeInfo item = obj.getEntries()[i];
            String line = String.format("|%03d| %s", (i + 1), item.getHexCode());
            System.out.println(line);
        }
        System.out.println();
    }
}
