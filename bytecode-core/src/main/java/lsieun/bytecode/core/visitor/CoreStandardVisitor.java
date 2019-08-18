package lsieun.bytecode.core.visitor;

import lsieun.bytecode.core.CompilerVersion;
import lsieun.bytecode.core.MagicNumber;
import lsieun.bytecode.core.Visitor;

public class CoreStandardVisitor implements Visitor {
    @Override
    public void visitMagicNumber(MagicNumber obj) {
        String line = String.format("magic='%s'", obj.getHexCode());
        System.out.println(line);
    }

    @Override
    public void visitCompilerVersion(CompilerVersion obj) {
        int major_version = obj.major_version;
        int version = major_version - 44;
        String line = String.format("compiler_version='%s' (Java %d)", obj.getHexCode(), version);
        System.out.println(line);
    }
}
