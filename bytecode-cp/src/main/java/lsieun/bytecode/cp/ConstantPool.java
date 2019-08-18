package lsieun.bytecode.cp;

import lsieun.bytecode.core.Node;
import lsieun.bytecode.core.cst.CPConst;
import lsieun.bytecode.core.exceptions.ClassFormatException;
import lsieun.utils.ByteDashboard;

public final class ConstantPool extends Node {
    public final int count;
    public final Constant[] entries;

    public ConstantPool(final ByteDashboard byteDashboard) {
        byte[] bytes = byteDashboard.peekN(2);
        super.setBytes(bytes);

        this.count = byteDashboard.readUnsignedShort();
        this.entries = new Constant[count];

        for (int i = 1; i < count; i++) {
            Constant item = Constant.readConstant(byteDashboard);
            item.index = i;

            this.entries[i] = item;
            /* Quote from the JVM specification:
             * "All eight byte constants take up two spots in the constant pool.
             * If this is the n'th byte in the constant pool, then the next item
             * will be numbered n+2"
             *
             * Thus we have to increment the index counter.
             */
            byte tag = item.tag;
            if ((tag == CPConst.CONSTANT_Double) || (tag == CPConst.CONSTANT_Long)) {
                i++;
            }
        }
    }

    public Constant getConstant(final int index) {
        if (index >= count || index < 0) {
            throw new ClassFormatException("Invalid constant pool reference: " + index
                    + ". Constant pool size is: " + this.count);
        }
        return entries[index];
    }

    public Constant getConstant(final int index, final byte tag) throws ClassFormatException {
        Constant c = getConstant(index);
        if (c == null) {
            throw new ClassFormatException("Constant pool at index " + index + " is null.");
        }
        if (c.tag != tag) {
            throw new ClassFormatException("Expected class '" + CPConst.getConstantName(tag)
                    + "' at index " + index + " and got " + c);
        }
        return c;
    }

    public String getConstantString(final int index, final byte tag) {
        Constant constant = getConstant(index, tag);
        return constant.value;
    }

    public void accept(Visitor v) {
        v.visitConstantPool(this);
    }

}
