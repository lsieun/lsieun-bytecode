package lsieun.bytecode.classfile.visitors;

import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.classfile.attrs.RuntimeInvisibleAnnotations;
import lsieun.bytecode.classfile.attrs.RuntimeVisibleAnnotations;
import lsieun.bytecode.classfile.attrs.Signature;
import lsieun.bytecode.classfile.attrs.annotation.*;
import lsieun.bytecode.classfile.attrs.classfile.*;
import lsieun.bytecode.classfile.attrs.method.AnnotationDefault;
import lsieun.bytecode.classfile.attrs.method.MethodParameter;
import lsieun.bytecode.classfile.attrs.method.MethodParameters;
import lsieun.bytecode.core.cst.AccessConst;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.cp.utils.ConstantPoolUtils;
import lsieun.utils.ByteDashboard;
import lsieun.utils.radix.HexUtils;

import java.util.Formatter;
import java.util.function.Consumer;

@SuppressWarnings("Duplicates")
public class AttributeStandardVisitor extends AbstractVisitor {
    private ConstantPool constant_pool;

    public AttributeStandardVisitor(ConstantPool constant_pool) {
        this.constant_pool = constant_pool;
        ConstantPoolUtils.simplify(constant_pool);
    }

    @Override
    public void visitAnnotationDefault(AnnotationDefault obj) {
        String hexCode = obj.getHexCode();
        System.out.println(String.format("%s%s:", System.lineSeparator(), "AnnotationDefault"));
        System.out.println("HexCode: " + hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();
        ElementValue default_value = obj.default_value;

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));


        displayElementValue(default_value, bd);
    }

    private void displayElementValue(ElementValue elementValue, ByteDashboard bd) {
        String format = "%s='%s' (%s)";
        System.out.println(String.format("%s='%s' (%c)", "tag", HexUtils.fromBytes(bd.nextN(1)), elementValue.type));
        if (elementValue instanceof SimpleElementValue) {
            SimpleElementValue simpleElementValue = (SimpleElementValue) elementValue;
            System.out.println(String.format(format, "const_value_index", HexUtils.fromBytes(bd.nextN(2)), simpleElementValue.stringifyValue()));
        }
        else if (elementValue instanceof EnumElementValue) {
            EnumElementValue enumElementValue = (EnumElementValue) elementValue;
            System.out.println(String.format(format, "type_name_index", HexUtils.fromBytes(bd.nextN(2)), enumElementValue.type_name_index));
            System.out.println(String.format(format, "const_name_index", HexUtils.fromBytes(bd.nextN(2)), enumElementValue.const_name_index));
        }
        else if (elementValue instanceof ClassElementValue) {
            ClassElementValue classElementValue = (ClassElementValue) elementValue;
            System.out.println(String.format(format, "class_info_index", HexUtils.fromBytes(bd.nextN(2)), classElementValue.class_info_index));
        }
        else if (elementValue instanceof ArrayElementValue) {
            ArrayElementValue arrayElementValue = (ArrayElementValue) elementValue;
            System.out.println(String.format(format, "num_values", HexUtils.fromBytes(bd.nextN(2)), arrayElementValue.num_values));
            for (int i=0; i<arrayElementValue.num_values; i++) {
                displayElementValue(arrayElementValue.entries[i], bd);
            }
        }
    }

    @Override
    public void visitBootstrapMethods(BootstrapMethods obj) {
        String hexCode = obj.getHexCode();
        System.out.println(String.format("%s%s:", System.lineSeparator(), "BootstrapMethods"));
        System.out.println("HexCode: " + hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));
        System.out.println(String.format(format, "num_bootstrap_methods", HexUtils.fromBytes(bd.nextN(2)), obj.num_bootstrap_methods));
        if (obj.num_bootstrap_methods > 0) {
            for (int i=0; i<obj.num_bootstrap_methods; i++) {
                BootstrapMethod entry = obj.entries[i];
                int bootstrap_method_ref = entry.bootstrap_method_ref;
                int num_bootstrap_arguments = entry.num_bootstrap_arguments;
                int[] bootstrap_arguments = entry.bootstrap_arguments;
                String bootstrap_arguments_str = array2str(bootstrap_arguments);
                System.out.println(String.format("|%03d|", (i + 1)));
                System.out.println(String.format(format, "bootstrap_method_ref", HexUtils.toHex(bd.nextN(2)), bootstrap_method_ref));
                System.out.println(String.format(format, "num_bootstrap_arguments", HexUtils.toHex(bd.nextN(2)), num_bootstrap_arguments));
                System.out.println(String.format(format, "bootstrap_arguments", HexUtils.toHex(bd.nextN(2 * num_bootstrap_arguments)), bootstrap_arguments_str));
            }
        }
    }

    public static String array2str(int[] array) {
        int length = array.length;
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("[");
        for (int i = 0; i < length - 1; i++) {
            int item = array[i];
            fm.format("#%s,", item);
        }
        fm.format("#%s", array[length - 1]);
        fm.format("]");
        return sb.toString();
    }

    @Override
    public void visitEnclosingMethod(EnclosingMethod obj) {
        String hexCode = obj.getHexCode();
        System.out.println(String.format("%s%s:", System.lineSeparator(), "EnclosingMethod"));
        System.out.println("HexCode: " + hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));
        System.out.println(String.format(format, "class_index", HexUtils.fromBytes(bd.nextN(2)), obj.class_index));
        String method_name = "";
        if (obj.method_index != 0) {
            method_name = constant_pool.getConstant(obj.method_index).value;
        }
        System.out.println(String.format(format, "method_index", HexUtils.fromBytes(bd.nextN(2)), obj.method_index));
    }

    @Override
    public void visitInnerClasses(InnerClasses obj) {
        String hexCode = obj.getHexCode();
        System.out.println(String.format("%s%s:", System.lineSeparator(), "InnerClasses"));
        System.out.println("HexCode: " + hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));
        System.out.println(String.format(format, "number_of_classes", HexUtils.fromBytes(bd.nextN(2)), obj.number_of_classes));

        if (obj.number_of_classes > 0) {
            for (int i = 0; i < obj.number_of_classes; i++) {
                InnerClass entry = obj.entries[i];
                System.out.println(System.lineSeparator());
                System.out.println(String.format("|%03d|", (i + 1)));
                System.out.println(String.format(format, "inner_class_info_index", HexUtils.fromBytes(bd.nextN(2)), entry.inner_class_info_index));
                System.out.println(String.format(format, "outer_class_info_index", HexUtils.fromBytes(bd.nextN(2)), entry.outer_class_info_index));
                System.out.println(String.format(format, "inner_name_index", HexUtils.fromBytes(bd.nextN(2)), entry.inner_name_index));
                System.out.println(String.format(format, "inner_class_access_flags", HexUtils.fromBytes(bd.nextN(2)), AccessConst.getInnerClassAccessFlagsString(entry.inner_class_access_flags)));

            }
        }
    }

    @Override
    public void visitRuntimeInvisibleAnnotations(RuntimeInvisibleAnnotations obj) {
        String hexCode = obj.getHexCode();
        System.out.println(String.format("%s%s:", System.lineSeparator(), "RuntimeInvisibleAnnotations"));
        System.out.println("HexCode: " + hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));
        System.out.println(String.format(format, "num_annotations", HexUtils.fromBytes(bd.nextN(2)), obj.num_annotations));
        if (obj.num_annotations > 0) {
            for (int i=0; i<obj.num_annotations; i++) {
                AnnotationEntry annotation = obj.annotations[i];
                displayAnnotationEntry(annotation, bd);
            }
        }
    }

    @Override
    public void visitRuntimeVisibleAnnotations(RuntimeVisibleAnnotations obj) {
        String hexCode = obj.getHexCode();
        System.out.println(String.format("%s%s:", System.lineSeparator(), "RuntimeInvisibleAnnotations"));
        System.out.println("HexCode: " + hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));
        System.out.println(String.format(format, "num_annotations", HexUtils.fromBytes(bd.nextN(2)), obj.num_annotations));
        if (obj.num_annotations > 0) {
            for (int i=0; i<obj.num_annotations; i++) {
                AnnotationEntry annotation = obj.annotations[i];
                displayAnnotationEntry(annotation, bd);
            }
        }
    }

    private void displayAnnotationEntry(AnnotationEntry annotationEntry, ByteDashboard bd) {
        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "type_index", HexUtils.fromBytes(bd.nextN(2)), annotationEntry.type_index));
        System.out.println(String.format(format, "num_element_value_pairs", HexUtils.fromBytes(bd.nextN(2)), annotationEntry.num_element_value_pairs));
        if (annotationEntry.num_element_value_pairs > 0) {
            for (int i=0; i<annotationEntry.num_element_value_pairs; i++) {
                ElementValuePair elementValuePair = annotationEntry.element_value_pair_list[i];
                System.out.println(String.format(format, "element_name_index", HexUtils.fromBytes(bd.nextN(2)), elementValuePair.element_name_index));
                displayElementValue(elementValuePair.value, bd);
            }
        }
    }

    @Override
    public void visitSignature(Signature obj) {
        String hexCode = obj.getHexCode();
        System.out.println(String.format("%s%s:", System.lineSeparator(), "Signature"));
        System.out.println("HexCode: " + hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));
        System.out.println(String.format(format, "signature_index", HexUtils.fromBytes(bd.nextN(2)), obj.signature_index));

    }

    @Override
    public void visitSourceFile(SourceFile obj) {
        String hexCode = obj.getHexCode();
        System.out.println(String.format("%s%s:", System.lineSeparator(), "SourceFile"));
        System.out.println("HexCode: " + hexCode);

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();

        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));
        System.out.println(String.format(format, "sourcefile_index", HexUtils.fromBytes(bd.nextN(2)), obj.sourcefile_index));

    }


    @Override
    public void visitMethodParameters(MethodParameters obj) {
        int parameters_count = obj.parameters_count;
        MethodParameter[] parameters = obj.parameters;

        visitStandAttribute(obj, (bd) -> {
            String format = "%s='%s' (%s)";
            System.out.println(String.format(format, "parameters_count", HexUtils.fromBytes(bd.nextN(1)), parameters_count));
            System.out.println("Name                           Flags");

            String format2 = "%-30s %s";
            for (int i=0; i<parameters_count;i++) {
                MethodParameter param = parameters[i];
                int name_index = param.name_index;
                int access_flags = param.access_flags;

                String name = constant_pool.getConstantString(name_index, CPConst.CONSTANT_Utf8);
                System.out.println(String.format(format2, HexUtils.fromBytes(bd.nextN(2)) + "(" + name_index + ":" + name + ")", HexUtils.fromBytes(bd.nextN(2)) + "(" + access_flags + ")"));
            }
        });
    }

    public void visitStandAttribute(AttributeInfo obj, Consumer<ByteDashboard> consumer) {
        String hexCode = obj.getHexCode();

        byte[] bytes = obj.getBytes();
        ByteDashboard bd = new ByteDashboard(bytes);
        int attribute_name_index = obj.getAttributeNameIndex();
        int attribute_length = obj.getAttributeLength();

        String attribute_name = constant_pool.getConstantString(attribute_name_index, CPConst.CONSTANT_Utf8);

        System.out.println(String.format("%s%s:", System.lineSeparator(), attribute_name));
        System.out.println("HexCode: " + hexCode);
        String format = "%s='%s' (%s)";
        System.out.println(String.format(format, "attribute_name_index", HexUtils.fromBytes(bd.nextN(2)), attribute_name_index));
        System.out.println(String.format(format, "attribute_length", HexUtils.fromBytes(bd.nextN(4)), attribute_length));

        consumer.accept(bd);
    }
}
