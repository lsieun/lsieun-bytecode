package lsieun.bytecode.classfile.utils;

import java.util.ArrayList;
import java.util.List;

import lsieun.bytecode.classfile.clazz.Attributes;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.clazz.FieldInfo;
import lsieun.bytecode.classfile.clazz.Fields;
import lsieun.utils.StringUtils;

public class FieldUtils {
    public static FieldInfo findField(ClassFile classFile, String nameAndType) {
        return findField(classFile.fields, nameAndType);
    }

    public static FieldInfo findField(Fields fields, String nameAndType) {
        if(StringUtils.isBlank(nameAndType)) return null;

        FieldInfo[] entries = fields.entries;
        for(int i = 0; i<entries.length; i++) {
            FieldInfo item = entries[i];
            String value = item.getValue();
            if(nameAndType.equals(value)) {
                return item;
            }
        }
        System.out.println("Field DOES NOT EXIST: " + nameAndType);
        displayAvailableFields(fields);
        return null;
    }

    public static void displayAvailableFields(Fields fields) {
        FieldInfo[] entries = fields.entries;
        if(entries != null && entries.length > 0) {
            System.out.println("\nAvailable Fields:");
            for(FieldInfo item : entries) {
                Attributes attributes = item.attributes;
                String attrNames = AttributeUtils.getAttributeNames(attributes);

                String format = "    Method='%s', AccessFlags='%s', Attrs='%s'";
                String line = String.format(format, item.getValue(), item.getAccessFlagsString(), attrNames);
                System.out.println(line);
            }
        }
    }

    public String getFieldNames(Fields fields) {
        FieldInfo[] entries = fields.entries;

        List<String> list = new ArrayList();
        for(FieldInfo item : entries) {
            String value = item.getValue();
            list.add(value);
        }
        return StringUtils.list2str(list, ", ");
    }
}
