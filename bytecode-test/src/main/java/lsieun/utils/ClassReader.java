package lsieun.utils;

import java.io.InputStream;

import lsieun.bytecode.classfile.attrs.method.Code;
import lsieun.bytecode.classfile.clazz.ClassFile;
import lsieun.bytecode.classfile.clazz.MethodInfo;
import lsieun.bytecode.classfile.utils.AttributeUtils;
import lsieun.bytecode.classfile.utils.MethodUtils;
import lsieun.utils.archive.JarUtils;
import lsieun.utils.io.FileUtils;

public class ClassReader {
    // region ClassFile
    public static ClassFile readClassFile() {
        ByteDashboard byteDashboard = readBytes();
        ClassFile classFile = new ClassFile(byteDashboard);
        return classFile;
    }

    public static ClassFile readClassFile(String filepath) {
        ByteDashboard byteDashboard = readBytes(filepath);
        ClassFile classFile = new ClassFile(byteDashboard);
        return classFile;
    }
    // endregion

    // region Method
    public static MethodInfo readMethod() {
        ClassFile classFile = readClassFile();
        return readMethod(classFile);
    }

    public static MethodInfo readMethod(ClassFile classFile) {
        String methodSignature = PropertyUtils.getProperty("bytecode.content.method.signature");
        return readMethod(classFile, methodSignature);
    }

    public static MethodInfo readMethod(ClassFile classFile, String signature) {
        return MethodUtils.findMethod(classFile, signature);
    }
    // endregion

    // region Code
    public static Code readCode() {
        ClassFile classFile = readClassFile();
        return readCode(classFile);
    }

    public static Code readCode(ClassFile classFile) {
        String methodSignature = PropertyUtils.getProperty("bytecode.content.method.signature");
        return readCode(classFile, methodSignature);
    }

    public static Code readCode(ClassFile classFile, String signature) {
        MethodInfo methodInfo = readMethod(classFile, signature);
        Code codeAttribute = AttributeUtils.findCodeAttribute(methodInfo);
        return codeAttribute;
    }
    // endregion

    // region Bytes
    public static ByteDashboard readBytes() {
        String url = null;
        byte[] bytes = null;

        final String type = PropertyUtils.getProperty("bytecode.source.type");
        if ("jar".equalsIgnoreCase(type)) {
            String jarPath = PropertyUtils.getProperty("bytecode.source.jar.path");
            String entryName = PropertyUtils.getProperty("bytecode.source.jar.entry.name");
            url = "jar:file:" + jarPath + "!/" + entryName;
            bytes = JarUtils.readClass(jarPath, entryName);
        } else if ("filepath".equalsIgnoreCase(type)) {
            String filepath = PropertyUtils.getProperty("bytecode.source.file.filepath");
            url = "file://" + filepath;
            bytes = FileUtils.readBytes(filepath);
        } else if ("classname".equalsIgnoreCase(type)) {
            String classname = PropertyUtils.getProperty("bytecode.source.class.fqcn");
            String filepath = FileUtils.getFilePath(ClassReader.class, classname);
            url = "file://" + filepath;
            bytes = FileUtils.readBytes(filepath);
        } else {
            String classname = PropertyUtils.getProperty("bytecode.source.classloader.fqcn");
            InputStream in = FileUtils.getInputStream(classname);
            bytes = FileUtils.readStream(in, true);
        }

        if (bytes == null) {
            throw new RuntimeException("bytes is null!!!");
        }

        System.out.printf("Class File Path: %s%n", url);
        ByteDashboard byteDashboard = new ByteDashboard(bytes);
        return byteDashboard;
    }

    public static ByteDashboard readBytes(String filepath) {
        byte[] bytes = FileUtils.readBytes(filepath);
        ByteDashboard byteDashboard = new ByteDashboard(bytes);
        return byteDashboard;
    }
    // endregion
}
