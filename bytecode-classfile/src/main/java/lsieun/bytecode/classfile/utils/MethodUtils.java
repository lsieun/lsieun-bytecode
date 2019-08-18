package lsieun.bytecode.classfile.utils;

import java.util.ArrayList;
import java.util.List;

import lsieun.bytecode.classfile.attrs.AttributeInfo;
import lsieun.bytecode.classfile.attrs.method.Code;
import lsieun.bytecode.classfile.clazz.Attributes;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.clazz.MethodInfo;
import lsieun.bytecode.classfile.clazz.Methods;
import lsieun.utils.StringUtils;

public class MethodUtils {
    // 接收方法名、签名，来找到具体的方法
    public static MethodInfo findMethod(ClassFile classFile, String nameAndType) {
        Methods methods = classFile.methods;
        return findMethod(methods, nameAndType);
    }

    public static MethodInfo findMethod(Methods methods, String nameAndType) {
        if(StringUtils.isBlank(nameAndType)) return null;

        MethodInfo[] entries = methods.entries;
        for(int i = 0; i<entries.length; i++) {
            MethodInfo item = entries[i];
            String value = item.getValue();
            if(nameAndType.equals(value)) {
                return item;
            }
        }

        System.out.println("Method DOES NOT EXIST: " + nameAndType);
        displayAvailableMethods(methods);
        return null;
    }

    public static void displayAvailableMethods(ClassFile classFile) {
        Methods methods = classFile.methods;
        displayAvailableMethods(methods);
    }

    public static void displayAvailableMethods(Methods methods) {
        MethodInfo[] entries = methods.entries;
        if(entries != null && entries.length > 0) {
            System.out.println("\nAvailable Methods:");
            for(MethodInfo item : entries) {
                Attributes attributes = item.attributes;
                String attrNames = AttributeUtils.getAttributeNames(attributes);

                String codeAttrs = "";
                AttributeInfo codeAttribute = AttributeUtils.findAttribute(attributes, "Code");
                if(codeAttribute != null) {
                    Code code = (Code) codeAttribute;
                    codeAttrs = AttributeUtils.getAttributeNames(code.attributes);
                }

                String format = "    Method='%s', AccessFlags='%s', Attrs='%s' CodeAttrs='%s'";
                String line = String.format(format, item.getValue(), item.getAccessFlagsString(), attrNames, codeAttrs);
                System.out.println(line);
            }
        }
    }


    public static String getMethodNames(Methods methods) {
        MethodInfo[] entries = methods.entries;
        List<String> list = new ArrayList();
        for(MethodInfo item : entries) {
            String value = item.getValue();
            list.add(value);
        }
        return StringUtils.list2str(list, ", ");
    }
}
