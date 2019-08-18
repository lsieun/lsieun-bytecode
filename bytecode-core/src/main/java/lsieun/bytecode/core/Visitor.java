package lsieun.bytecode.core;

public interface Visitor {

    void visitMagicNumber(MagicNumber obj);

    void visitCompilerVersion(CompilerVersion obj);

}
