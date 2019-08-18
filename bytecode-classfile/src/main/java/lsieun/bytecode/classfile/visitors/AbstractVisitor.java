package lsieun.bytecode.classfile.visitors;

import lsieun.bytecode.classfile.Visitor;
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

public class AbstractVisitor implements Visitor {

    // region ClassFile
    @Override
    public void visitClassFile(ClassFile obj) {

    }

    @Override
    public void visitClassInfo(ClassInfo obj) {

    }

    @Override
    public void visitFields(Fields obj) {

    }

    @Override
    public void visitFieldInfo(FieldInfo obj) {

    }

    @Override
    public void visitMethods(Methods obj) {

    }

    @Override
    public void visitMethodInfo(MethodInfo obj) {

    }

    @Override
    public void visitAttributes(Attributes obj) {

    }

    @Override
    public void visitAttributeInfo(AttributeInfo obj) {

    }


    // endregion

    // region attributes
    @Override
    public void visitAnnotationDefault(AnnotationDefault obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitBootstrapMethods(BootstrapMethods obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitCode(Code obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitConstantValue(ConstantValue obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitDeprecated(Deprecated obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitEnclosingMethod(EnclosingMethod obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitExceptions(Exceptions obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitInnerClasses(InnerClasses obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitLineNumberTable(LineNumberTable obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitLocalVariableTable(LocalVariableTable obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitLocalVariableTypeTable(LocalVariableTypeTable obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitMethodParameters(MethodParameters obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitRuntimeInvisibleAnnotations(RuntimeInvisibleAnnotations obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitRuntimeVisibleAnnotations(RuntimeVisibleAnnotations obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitSignature(Signature obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitSourceFile(SourceFile obj) {
        visitAttributeInfo(obj);
    }

    @Override
    public void visitStackMapTable(StackMapTable obj) {
        visitAttributeInfo(obj);
    }
    // endregion
}
