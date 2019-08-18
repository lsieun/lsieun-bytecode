package lsieun.bytecode.cp.utils;

import java.util.ArrayList;
import java.util.List;

import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.cp.ConstantPool;
import lsieun.bytecode.cp.*;
import lsieun.bytecode.core.cst.CPConst;

public class ConstantPoolUtils {
    public static void simplify(ConstantPool constantPool) {
        merge(constantPool);
    }

    private static void merge(ConstantPool constantPool) {
        Constant[] entries = constantPool.entries;
        List<Constant> list1 = new ArrayList();
        List<Constant> list2 = new ArrayList();
        List<Constant> list3 = new ArrayList();
        List<Constant> list4 = new ArrayList();

        for(int i = 0; i<entries.length; i++) {
            Constant item = entries[i];
            if(item == null) continue;
            byte tag = item.tag;

            if(tag == CPConst.CONSTANT_Utf8 ||
                    tag == CPConst.CONSTANT_Integer ||
                    tag == CPConst.CONSTANT_Float ||
                    tag == CPConst.CONSTANT_Long ||
                    tag == CPConst.CONSTANT_Double
            ) {
                list1.add(item);
            }
            else if(tag == CPConst.CONSTANT_Class ||
                    tag == CPConst.CONSTANT_String ||
                    tag == CPConst.CONSTANT_NameAndType ||
                    tag == CPConst.CONSTANT_MethodType ||
                    tag == CPConst.CONSTANT_Module ||
                    tag == CPConst.CONSTANT_Package
            ) {
                list2.add(item);
            }
            else if(tag == CPConst.CONSTANT_Fieldref ||
                    tag == CPConst.CONSTANT_Methodref ||
                    tag == CPConst.CONSTANT_InterfaceMethodref ||
                    tag == CPConst.CONSTANT_Dynamic ||
                    tag == CPConst.CONSTANT_InvokeDynamic
            ) {
                list3.add(item);
            }
            else if(tag == CPConst.CONSTANT_MethodHandle) {
                list4.add(item);
            }
            else {
                continue;
            }

            processList2(list2, constantPool);
            processList3(list3, constantPool);
            processList4(list4, constantPool);
        }
    }

    private static void processList2(List<Constant> list, ConstantPool constantPool) {
        for(int i=0; i<list.size(); i++) {
            Constant item = list.get(i);
            byte tag = item.tag;

            if(tag == CPConst.CONSTANT_Class) {
                ConstantClass sub = (ConstantClass) item;
                item.value = constantPool.getConstantString(sub.name_index, CPConst.CONSTANT_Utf8);
            }
            else if(tag == CPConst.CONSTANT_String) {
                ConstantString sub = (ConstantString) item;
                item.value = constantPool.getConstantString(sub.string_index, CPConst.CONSTANT_Utf8);
            }
            else if(tag == CPConst.CONSTANT_NameAndType) {
                ConstantNameAndType sub = (ConstantNameAndType) item;
                String name = constantPool.getConstantString(sub.name_index, CPConst.CONSTANT_Utf8);
                String descriptor = constantPool.getConstantString(sub.descriptor_index, CPConst.CONSTANT_Utf8);
                item.value = name + ":" + descriptor;
            }
            else if(tag == CPConst.CONSTANT_MethodType) {
                ConstantMethodType sub = (ConstantMethodType) item;
                item.value = constantPool.getConstantString(sub.descriptor_index, CPConst.CONSTANT_Utf8);
            }
            else if(tag == CPConst.CONSTANT_Module) {
                ConstantModule sub = (ConstantModule) item;
                item.value = constantPool.getConstantString(sub.name_index, CPConst.CONSTANT_Utf8);
            }
            else if(tag == CPConst.CONSTANT_Package) {
                ConstantPackage sub = (ConstantPackage) item;
                item.value = constantPool.getConstantString(sub.name_index, CPConst.CONSTANT_Utf8);
            }
            else {
                continue;
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private static void processList3(List<Constant> list, ConstantPool constantPool) {
        for(int i=0; i<list.size(); i++) {
            Constant item = list.get(i);
            byte tag = item.tag;

            if(tag == CPConst.CONSTANT_Fieldref) {
                ConstantFieldref sub = (ConstantFieldref) item;
                int classIndex = sub.getClassIndex();
                int nameAndTypeIndex = sub.getNameAndTypeIndex();

                String className = constantPool.getConstantString(classIndex, CPConst.CONSTANT_Class);
                String nameAndType = constantPool.getConstantString(nameAndTypeIndex, CPConst.CONSTANT_NameAndType);
                item.value = className + "." + nameAndType;
            }
            else if(tag == CPConst.CONSTANT_Methodref) {
                ConstantMethodref sub = (ConstantMethodref) item;
                int classIndex = sub.getClassIndex();
                int nameAndTypeIndex = sub.getNameAndTypeIndex();

                String className = constantPool.getConstantString(classIndex, CPConst.CONSTANT_Class);
                String nameAndType = constantPool.getConstantString(nameAndTypeIndex, CPConst.CONSTANT_NameAndType);
                item.value = className + "." + nameAndType;
            }
            else if(tag == CPConst.CONSTANT_InterfaceMethodref) {
                ConstantInterfaceMethodref sub = (ConstantInterfaceMethodref) item;
                int classIndex = sub.getClassIndex();
                int nameAndTypeIndex = sub.getNameAndTypeIndex();

                String className = constantPool.getConstantString(classIndex, CPConst.CONSTANT_Class);
                String nameAndType = constantPool.getConstantString(nameAndTypeIndex, CPConst.CONSTANT_NameAndType);
                item.value = className + "." + nameAndType;
            }
            else if(tag == CPConst.CONSTANT_Dynamic) {
                ConstantDynamic sub = (ConstantDynamic) item;
                item.value = constantPool.getConstantString(sub.name_and_type_index, CPConst.CONSTANT_NameAndType);
            }
            else if(tag == CPConst.CONSTANT_InvokeDynamic) {
                ConstantInvokeDynamic sub = (ConstantInvokeDynamic) item;
                item.value = constantPool.getConstantString(sub.name_and_type_index, CPConst.CONSTANT_NameAndType);
            }
            else {
                continue;
            }
        }
    }

    private static void processList4(List<Constant> list, ConstantPool constantPool) {
        for(int i=0; i<list.size(); i++) {
            Constant item = list.get(i);
            byte tag = item.tag;

            if(tag == CPConst.CONSTANT_MethodHandle) {
                ConstantMethodHandle sub = (ConstantMethodHandle) item;

                Constant target = constantPool.getConstant(sub.reference_index);
                item.value = target.value;
            }
        }
    }

}
