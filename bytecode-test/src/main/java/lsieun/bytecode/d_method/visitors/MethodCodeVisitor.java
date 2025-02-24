package lsieun.bytecode.d_method.visitors;

import java.util.Arrays;

import lsieun.bytecode.classfile.attrs.code.LocalVariable;
import lsieun.bytecode.classfile.attrs.code.LocalVariableTable;
import lsieun.bytecode.classfile.attrs.code.LocalVariableType;
import lsieun.bytecode.classfile.attrs.code.LocalVariableTypeTable;
import lsieun.bytecode.classfile.attrs.method.Code;
import lsieun.bytecode.classfile.attrs.method.ExceptionTable;
import lsieun.bytecode.classfile.attrs.method.MethodParameter;
import lsieun.bytecode.classfile.attrs.method.MethodParameters;
import lsieun.bytecode.classfile.clazz.Attributes;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.clazz.MethodInfo;
import lsieun.bytecode.classfile.utils.AttributeUtils;
import lsieun.bytecode.cp.utils.ConstantPoolUtils;
import lsieun.bytecode.classfile.utils.MethodUtils;
import lsieun.bytecode.classfile.visitors.AbstractVisitor;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.opcode.utils.InstructionChain;
import lsieun.bytecode.f_opcode.visitors.BaroqueOpcodeVisitor;
import lsieun.bytecode.opcode.visitors.OpcodeReadVisitor;
import lsieun.utils.radix.HexUtils;

@SuppressWarnings("Duplicates")
public class MethodCodeVisitor extends AbstractVisitor {
    private String name_and_type;
    private ConstantPool constant_pool;

    public MethodCodeVisitor(String name_and_type) {
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
        final int name_index = obj.name_index;
        final int descriptor_index = obj.descriptor_index;

        String name = constant_pool.getConstant(name_index).value;
        String descriptor = constant_pool.getConstant(descriptor_index).value;
        System.out.println(String.format("Method %s:%s", name, descriptor));

        Code code = AttributeUtils.findCodeAttribute(obj);
        if (code == null) {
            System.out.println("NO CODE");
            return;
        }
        code.accept(this);
    }

    @Override
    public void visitCode(Code obj) {
        int max_stack = obj.max_stack;
        int max_locals = obj.max_locals;
        int code_length = obj.code_length;
        byte[] code_bytes = obj.code;
        ExceptionTable[] exception_table_array = obj.exception_table_array;
        Attributes attributes = obj.attributes;

        System.out.println(System.lineSeparator());
        System.out.println("=== === ===  === === ===  === === ===");
        String format = "%s = %s";
        System.out.println(String.format(format, "max_stack", max_stack));
        System.out.println(String.format(format, "max_locals", max_locals));
        System.out.println(String.format(format, "code_length", code_length));
        System.out.println(String.format(format, "code", HexUtils.fromBytes(code_bytes)));

        System.out.println("\r\n=== === ===  === === ===  === === ===");
        BaroqueOpcodeVisitor v = new BaroqueOpcodeVisitor();

        OpcodeReadVisitor rv = new OpcodeReadVisitor(code_bytes);
        InstructionChain chain = rv.getInstructionChain();
        v.visit(constant_pool, code_bytes, chain);

        LocalVariableTable localVariableTable = (LocalVariableTable) AttributeUtils.findAttribute(attributes, "LocalVariableTable");
        if (localVariableTable != null) {
            localVariableTable.accept(this);
        }

        LocalVariableTypeTable localVariableTypeTable = (LocalVariableTypeTable) AttributeUtils.findAttribute(attributes, "LocalVariableTypeTable");
        if (localVariableTypeTable != null) {
            localVariableTypeTable.accept(this);
        }

        if (exception_table_array != null && exception_table_array.length > 0) {
            System.out.println("\r\nException Table:");
            String exception_title = "from    to  target  type";
            System.out.println(exception_title);
            String exception_format = "%4d  %4d  %6d  %s";
            for (ExceptionTable item : exception_table_array) {
                int start_pc = item.start_pc;
                int end_pc = item.end_pc;
                int handler_pc = item.handler_pc;
                int catch_type = item.catch_type;


                String catch_type_value = (catch_type == 0)?"All Exceptions(catch_type = 0)":constant_pool.getConstant(catch_type).value;

                System.out.println(String.format(exception_format, start_pc, end_pc, handler_pc, catch_type_value));
            }
        }
    }

    @Override
    public void visitLocalVariableTable(LocalVariableTable obj) {
        LocalVariable[] entries = obj.getEntries();
        Arrays.sort(entries);
        System.out.println("\r\nLocalVariableTable:");
        String title = "index  start_pc  length  name_and_type";
        System.out.println(title);
        String format = "%5d  %8d  %6d  %s:%s";
        for (LocalVariable entry : entries) {
            int index = entry.getIndex();
            int start_pc = entry.getStartPC();
            int length = entry.getLength();
            int name_index = entry.getNameIndex();
            int descriptor_index = entry.getDescriptorIndex();

            String name = constant_pool.getConstant(name_index).value;
            String descriptor = constant_pool.getConstant(descriptor_index).value;
            System.out.println(String.format(format, index, start_pc, length, name, descriptor));
        }
    }

    @Override
    public void visitLocalVariableTypeTable(LocalVariableTypeTable obj) {
        LocalVariableType[] entries = obj.getEntries();
        Arrays.sort(entries);
        System.out.println("LocalVariableTypeTable:");
        String title = "index  start_pc  length  name_and_type";
        System.out.println(title);
        String format = "%5d  %8d  %6d  %s:%s";
        for (LocalVariableType entry : entries) {
            int index = entry.getIndex();
            int start_pc = entry.getStartPC();
            int length = entry.getLength();
            int name_index = entry.getNameIndex();
            int signature_index = entry.getSignatureIndex();

            String name = constant_pool.getConstant(name_index).value;
            String signature = constant_pool.getConstant(signature_index).value;
            System.out.println(String.format(format, index, start_pc, length, name, signature));
        }
    }
}
