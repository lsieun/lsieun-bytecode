package lsieun.bytecode.core.visitor;

import lsieun.bytecode.core.CompilerVersion;
import lsieun.bytecode.core.MagicNumber;
import lsieun.bytecode.core.Visitor;

public class CoreRawVisitor implements Visitor {

    @Override
    public void visitMagicNumber(MagicNumber obj) {
        System.out.println("magic");
        System.out.println(obj.getHexCode());
        System.out.println();
    }

    @Override
    public void visitCompilerVersion(CompilerVersion obj) {
        System.out.println("compiler_version");
        System.out.println(obj.getHexCode());
        System.out.println();
    }

}
