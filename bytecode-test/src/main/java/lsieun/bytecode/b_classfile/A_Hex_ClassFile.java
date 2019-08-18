package lsieun.bytecode.b_classfile;

import lsieun.utils.ByteDashboard;
import lsieun.utils.ClassReader;
import lsieun.utils.radix.HexUtils;

public class A_Hex_ClassFile {
    public static void main(String[] args) {
        ByteDashboard byteDashboard = ClassReader.readBytes();
        System.out.println("\r\n");
        System.out.println("=== === ===  === === ===  === === ===  === === ===");
        System.out.println(HexUtils.getPrettyFormat(HexUtils.fromBytes(byteDashboard.getBytes())));
    }
}
