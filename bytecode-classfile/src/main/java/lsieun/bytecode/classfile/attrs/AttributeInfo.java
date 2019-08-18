package lsieun.bytecode.classfile.attrs;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.classfile.BootstrapMethods;
import lsieun.bytecode.classfile.attrs.classfile.EnclosingMethod;
import lsieun.bytecode.classfile.attrs.classfile.InnerClasses;
import lsieun.bytecode.classfile.attrs.classfile.SourceFile;
import lsieun.bytecode.classfile.attrs.code.LineNumberTable;
import lsieun.bytecode.classfile.attrs.code.LocalVariableTable;
import lsieun.bytecode.classfile.attrs.code.LocalVariableTypeTable;
import lsieun.bytecode.classfile.attrs.code.StackMapTable;
import lsieun.bytecode.classfile.attrs.field.ConstantValue;
import lsieun.bytecode.classfile.attrs.method.AnnotationDefault;
import lsieun.bytecode.classfile.attrs.method.Code;
import lsieun.bytecode.classfile.attrs.method.Exceptions;
import lsieun.bytecode.classfile.attrs.method.MethodParameters;
import lsieun.bytecode.core.Node;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.ByteUtils;

public class AttributeInfo extends Node {
    private final int attribute_name_index;
    private final int attribute_length;

    private final String name;
    private String value;

    public AttributeInfo(ByteDashboard byteDashboard, ConstantPool constantPool) {
        int length = byteDashboard.peekInt(2, 4);
        byte[] bytes = byteDashboard.peekN(6 + length);
        super.setBytes(bytes);

        this.attribute_name_index = byteDashboard.readUnsignedShort();
        this.attribute_length = byteDashboard.readInt();

        this.name = constantPool.getConstantString(attribute_name_index, CPConst.CONSTANT_Utf8);
    }

    public AttributeInfo(ByteDashboard byteDashboard, ConstantPool constantPool, boolean readRemain) {
        this(byteDashboard, constantPool);
        if (readRemain) {
            byteDashboard.nextN(this.attribute_length);
        }
    }

    // region getter
    public int getAttributeNameIndex() {
        return attribute_name_index;
    }

    public int getAttributeLength() {
        return attribute_length;
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

    // endregion

    public static AttributeInfo read(ByteDashboard byteDashboard, ConstantPool constantPool) {
        byte[] attribute_name_index_bytes = byteDashboard.peekN(2);
        int attributeNameIndex = ByteUtils.bytesToInt(attribute_name_index_bytes, 0);

        String name = constantPool.getConstantString(attributeNameIndex, CPConst.CONSTANT_Utf8);
        AttributeInfo instance = null;

        if ("SourceFile".equals(name)) {
            instance = new SourceFile(byteDashboard, constantPool);
        } else if ("InnerClasses".equals(name)) {
            instance = new InnerClasses(byteDashboard, constantPool);
        } else if ("Code".equals(name)) {
            instance = new Code(byteDashboard, constantPool);
        } else if ("LineNumberTable".equals(name)) {
            instance = new LineNumberTable(byteDashboard, constantPool);
        } else if ("LocalVariableTable".equals(name)) {
            instance = new LocalVariableTable(byteDashboard, constantPool);
        } else if ("LocalVariableTypeTable".equals(name)) {
            instance = new LocalVariableTypeTable(byteDashboard, constantPool);
        } else if ("Signature".equals(name)) {
            instance = new Signature(byteDashboard, constantPool);
        } else if ("Deprecated".equals(name)) {
            instance = new Deprecated(byteDashboard, constantPool);
        } else if ("Exceptions".equals(name)) {
            instance = new Exceptions(byteDashboard, constantPool);
        } else if ("ConstantValue".equals(name)) {
            instance = new ConstantValue(byteDashboard, constantPool);
        } else if ("RuntimeVisibleAnnotations".equals(name)) {
            instance = new RuntimeVisibleAnnotations(byteDashboard, constantPool);
        }
        else if ("StackMapTable".equals(name)) {
            instance = new StackMapTable(byteDashboard, constantPool);
        }
        else if ("MethodParameters".equals(name)) {
            instance = new MethodParameters(byteDashboard, constantPool);
        }
        else if ("EnclosingMethod".equals(name)) {
            instance = new EnclosingMethod(byteDashboard, constantPool);
        }
        else if ("AnnotationDefault".equals(name)) {
            instance = new AnnotationDefault(byteDashboard, constantPool);
        }
        else if ("RuntimeInvisibleAnnotations".equals(name)) {
            instance = new RuntimeInvisibleAnnotations(byteDashboard, constantPool);
        }
        else if ("BootstrapMethods".equals(name)) {
            instance = new BootstrapMethods(byteDashboard, constantPool);
        }
        else {
            throw new RuntimeException("Unknown Attribute Name: " + name);
            //instance = new AttributeInfo(byteDashboard, constantPool, true);
        }


        return instance;
    }

    public void accept(Visitor v) {
        v.visitAttributeInfo(this);
    }
}
