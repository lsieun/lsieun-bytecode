package lsieun.bytecode.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import lsieun.bytecode.core.cst.TypeConst;
import lsieun.bytecode.core.utils.Utility;

public class UtilityTest {

    @Test
    public void typeOfSignature() {
        assertEquals(TypeConst.T_BOOLEAN, Utility.typeOfSignature("Z"));
        assertEquals(TypeConst.T_CHAR, Utility.typeOfSignature("C"));
        assertEquals(TypeConst.T_FLOAT, Utility.typeOfSignature("F"));
        assertEquals(TypeConst.T_DOUBLE, Utility.typeOfSignature("D"));
        assertEquals(TypeConst.T_BYTE, Utility.typeOfSignature("B"));
        assertEquals(TypeConst.T_SHORT, Utility.typeOfSignature("S"));
        assertEquals(TypeConst.T_INT, Utility.typeOfSignature("I"));
        assertEquals(TypeConst.T_LONG, Utility.typeOfSignature("J"));
        assertEquals(TypeConst.T_VOID, Utility.typeOfSignature("V"));
        assertEquals(TypeConst.T_ARRAY, Utility.typeOfSignature("[I"));
        assertEquals(TypeConst.T_OBJECT, Utility.typeOfSignature("Ljava/lang/String"));
        assertEquals(TypeConst.T_OBJECT, Utility.typeOfSignature("!+*Ljava/lang/String"));
    }

    @Test
    public void signatureToString() {
        assertEquals("void", Utility.signatureToString("V", false));
        assertEquals("boolean", Utility.signatureToString("Z", false));
        assertEquals("byte", Utility.signatureToString("B", false));
        assertEquals("short", Utility.signatureToString("S", false));
        assertEquals("char", Utility.signatureToString("C", false));
        assertEquals("int", Utility.signatureToString("I", false));
        assertEquals("long", Utility.signatureToString("J", false));
        assertEquals("float", Utility.signatureToString("F", false));
        assertEquals("double", Utility.signatureToString("D", false));
        assertEquals("java.lang.String", Utility.signatureToString("Ljava/lang/String;", false));
        assertEquals("int[]", Utility.signatureToString("[I", false));
        assertEquals("java.io.Serializable[]", Utility.signatureToString("[Ljava/io/Serializable;", false));
        assertEquals("int[][]", Utility.signatureToString("[[I", false));
        assertEquals("java.io.Serializable[][]", Utility.signatureToString("[[Ljava/io/Serializable;", false));
    }

    @Test
    public void compactClassName() {
        assertEquals("java.lang.String", Utility.compactClassName("java/lang/String", false));
        assertEquals("String", Utility.compactClassName("java/lang/String", true));
        assertEquals("java.io.Serializable", Utility.compactClassName("java/io/Serializable", false));
        assertEquals("java.io.Serializable", Utility.compactClassName("java/io/Serializable", true));
    }

}