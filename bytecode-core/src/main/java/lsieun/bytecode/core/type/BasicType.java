package lsieun.bytecode.core.type;

import lsieun.bytecode.core.cst.TypeConst;
import lsieun.bytecode.core.exceptions.ClassGenException;
import lsieun.bytecode.core.utils.TypeUtils;

/**
 * Denotes basic type such as int.
 */
public final class BasicType extends Type {
    /**
     * Constructor for basic types such as int, long, `void'
     *
     * @param type one of T_INT, T_BOOLEAN, ..., T_VOID
     * @see TypeConst
     */
    public BasicType(final byte type) {
        super(type, TypeConst.getShortTypeName(type));
        if ((type < TypeConst.T_BOOLEAN) || (type > TypeConst.T_VOID)) {
            throw new ClassGenException("Invalid type: " + type);
        }
    }

}
