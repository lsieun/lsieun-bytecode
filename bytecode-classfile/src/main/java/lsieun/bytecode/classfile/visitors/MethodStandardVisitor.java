package lsieun.bytecode.classfile.visitors;

import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.classfile.attrs.Signature;
import lsieun.bytecode.classfile.attrs.method.*;
import lsieun.bytecode.classfile.clazz.Attributes;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.classfile.clazz.MethodInfo;
import lsieun.bytecode.cp.utils.ConstantPoolUtils;
import lsieun.bytecode.classfile.utils.MethodUtils;
import lsieun.bytecode.core.cst.AccessConst;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.HexUtils;

@SuppressWarnings("Duplicates")
public class MethodStandardVisitor extends AbstractVisitor {
    private String name_and_type;
    private ConstantPool constant_pool;

    public MethodStandardVisitor(String name_and_type) {
        this.name_and_type = name_and_type;
    }

    @Override
    public void visitClassFile(ClassFile obj) {
        constant_pool = obj.constantPool;
        ConstantPoolUtils.simplify(constant_pool);
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
    public void visitCode(Code obj) {
        String hexCode = obj.getHexCode();
        System.out.println(hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();
        int max_stack = obj.max_stack;
        int max_locals = obj.max_locals;
        int code_length = obj.code_length;
        int exception_table_length = obj.exception_table_length;
        ExceptionTable[] exception_table_array = obj.exception_table_array;
        int attributes_count = obj.getAttributesCount();

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));
        System.out.println(String.format(format, "max_stack", HexUtils.fromBytes(bd.nextN(2)), max_stack));
        System.out.println(String.format(format, "max_locals", HexUtils.fromBytes(bd.nextN(2)), max_locals));
        System.out.println(String.format(format, "code_length", HexUtils.fromBytes(bd.nextN(4)), code_length));

        byte[] code_bytes = obj.code;
        byte[] codes = bd.nextN(code_length);
        System.out.println("code: " + HexUtils.fromBytes(code_bytes));
        System.out.println("code: " + HexUtils.fromBytes(codes));
        System.out.println(String.format(format, "exception_table_length", HexUtils.fromBytes(bd.nextN(2)), exception_table_length));

        if (exception_table_length > 0) {
            System.out.println("exception_table:");
            for (int i = 0; i < exception_table_length; i++) {
                ExceptionTable exceptionTable = exception_table_array[i];
                int start_pc = exceptionTable.start_pc;
                int end_pc = exceptionTable.end_pc;
                int handler_pc = exceptionTable.handler_pc;
                int catch_type = exceptionTable.catch_type;
                String line = String.format("    %s (start_pc='%d', end_pc='%d', handler_pc='%d', catch_type='%d')",
                        HexUtils.fromBytes(bd.nextN(8)), start_pc, end_pc, handler_pc, catch_type);
                System.out.println(line);
            }
        }
        System.out.println(String.format(format, "attributes_count", HexUtils.fromBytes(bd.nextN(2)), attributes_count));

    }

    @Override
    public void visitExceptions(Exceptions obj) {
        String hexCode = obj.getHexCode();
        System.out.println(hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();
        int number_of_exceptions = obj.getNumberOfExceptions();
        int[] exception_index_array = obj.getExceptionIndexArray();

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));
        System.out.println(String.format(format, "number_of_exceptions", HexUtils.fromBytes(bd.nextN(2)), number_of_exceptions));

        System.out.println("exception_index_table:");
        for (int cpIndex : exception_index_array) {
            System.out.println(String.format("    %s (%d) || %s", HexUtils.fromBytes(bd.nextN(2)), cpIndex, constant_pool.getConstant(cpIndex).value));
        }
    }

    @Override
    public void visitSignature(Signature obj) {
        String hexCode = obj.getHexCode();
        System.out.println(hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();
        int signature_index = obj.signature_index;

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));
        System.out.println(String.format(format, "signature_index", HexUtils.fromBytes(bd.nextN(2)), signature_index));
    }

    @Override
    public void visitMethodParameters(MethodParameters obj) {
        String hexCode = obj.getHexCode();
        System.out.println(hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));
        System.out.println(String.format(format, "parameters_count", HexUtils.fromBytes(bd.nextN(1)), obj.parameters_count));

        if (obj.parameters_count > 0) {
            System.out.println("MethodParameters:");
            System.out.println("Name      Flags");
            for(int i=0; i<obj.parameters_count; i++) {
                MethodParameter param = obj.parameters[i];
                byte[] name_index_bytes = bd.nextN(2);
                byte[] access_flags_bytes = bd.nextN(2);
                System.out.println(String.format("%-10s%s(%s)", constant_pool.getConstantString(param.name_index, CPConst.CONSTANT_Utf8), HexUtils.fromBytes(access_flags_bytes), AccessConst.getMethodParameterAccessFlagsString(param.access_flags)));
            }
        }
    }
}
