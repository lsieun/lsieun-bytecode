package lsieun.bytecode.classfile.attrs;

import lsieun.bytecode.classfile.Visitor;
import lsieun.bytecode.classfile.attrs.annotation.AnnotationEntry;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.utils.ByteDashboard;

public class RuntimeInvisibleAnnotations extends AttributeInfo {
    public final int num_annotations;
    public final AnnotationEntry[] annotations;

    public RuntimeInvisibleAnnotations(ByteDashboard byteDashboard, ConstantPool constantPool) {
        super(byteDashboard, constantPool);


        this.num_annotations = byteDashboard.readUnsignedShort();
        this.annotations = new AnnotationEntry[num_annotations];

        for (int i = 0; i < num_annotations; i++) {
            AnnotationEntry item = new AnnotationEntry(byteDashboard, constantPool);
            this.annotations[i] = item;
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitRuntimeInvisibleAnnotations(this);
    }
}
