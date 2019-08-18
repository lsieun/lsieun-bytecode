package lsieun.bytecode.cp.visitor;

import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.cp.Constant;
import lsieun.bytecode.cp.ConstantPool;

public class CPStandardVisitor extends AbstractVisitor {

    @Override
    public void visitConstantPool(ConstantPool obj) {
        int count = obj.count;
        Constant[] entries = obj.entries;

        String countLine = String.format("constant_pool_count='%s' (%d)", obj.getHexCode(), count);
        System.out.println(countLine);

        System.out.println("constant_pool");
        for(Constant item : entries) {
            if(item == null) continue;
            item.accept(this);
        }
    }

    @Override
    public void visitConstant(Constant obj) {
        String line = String.format("    |%03d| %s {Value='%s', HexCode='%s'}",
                obj.index,
                CPConst.getConstantName(obj.tag),
                obj.value,
                obj.getHexCode());
        System.out.println(line);
    }
}
