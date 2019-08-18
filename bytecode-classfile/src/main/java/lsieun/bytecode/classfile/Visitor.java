package lsieun.bytecode.classfile;

import lsieun.bytecode.classfile.attrs.*;
import lsieun.bytecode.classfile.attrs.Deprecated;
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
import lsieun.bytecode.classfile.clazz.*;

public interface Visitor {
    void visitClassFile(ClassFile obj);

    void visitClassInfo(ClassInfo obj);

    void visitFields(Fields obj);

    void visitFieldInfo(FieldInfo obj);

    void visitMethods(Methods obj);

    void visitMethodInfo(MethodInfo obj);

    void visitAttributes(Attributes obj);

    void visitAttributeInfo(AttributeInfo obj);



    // region attributes
    void visitAnnotationDefault(AnnotationDefault obj);

    void visitBootstrapMethods(BootstrapMethods obj);

    void visitCode(Code obj);

    void visitConstantValue(ConstantValue obj);

    void visitDeprecated(Deprecated obj);

    void visitEnclosingMethod(EnclosingMethod obj);

    void visitExceptions(Exceptions obj);

    void visitInnerClasses(InnerClasses obj);

    void visitLineNumberTable(LineNumberTable obj);

    void visitLocalVariableTable(LocalVariableTable obj);

    void visitLocalVariableTypeTable(LocalVariableTypeTable obj);

    void visitMethodParameters(MethodParameters obj);

    void visitRuntimeInvisibleAnnotations(RuntimeInvisibleAnnotations obj);

    void visitRuntimeVisibleAnnotations(RuntimeVisibleAnnotations obj);

    void visitSignature(Signature obj);

    void visitSourceFile(SourceFile obj);

    void visitStackMapTable(StackMapTable obj);
    // endregion
}
