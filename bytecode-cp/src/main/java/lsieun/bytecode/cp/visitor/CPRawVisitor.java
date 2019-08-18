package lsieun.bytecode.cp.visitor;

import lsieun.bytecode.cp.Constant;
import lsieun.bytecode.cp.ConstantPool;

public class CPRawVisitor extends AbstractVisitor {

    @Override
    public void visitConstantPool(ConstantPool obj) {
        System.out.println("constant_pool_count");
        System.out.println(obj.getHexCode());
        System.out.println();

        System.out.println("constant_pool");
        for (Constant item : obj.entries) {
            if (item == null) continue;
            String line = String.format("|%03d| %s", item.index, item.getHexCode());
            System.out.println(line);
        }
        System.out.println();
    }

}
