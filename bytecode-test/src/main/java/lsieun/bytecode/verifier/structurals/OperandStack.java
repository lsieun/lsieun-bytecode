package lsieun.bytecode.verifier.structurals;

import java.util.ArrayList;

import lsieun.bytecode.core.type.*;
import lsieun.bytecode.core.utils.TypeUtils;

/**
 * This class implements a f_stack used for symbolic JVM f_stack simulation.
 * [It's used an an operand f_stack substitute.]
 * Elements of this f_stack are {@link Type} objects.
 */
public class OperandStack implements Cloneable {
    /** We hold the f_stack information here. */
    private ArrayList<Type> stack = new ArrayList();

    /** The maximum number of f_stack slots this OperandStack instance may hold. */
    private final int maxStack;

    /**
     * Creates an empty f_stack with a maximum of maxStack slots.
     */
    public OperandStack(final int maxStack) {
        this.maxStack = maxStack;
    }

    /**
     * Creates an otherwise empty f_stack with a maximum of maxStack slots and
     * the ObjectType 'obj' at the top.
     */
    public OperandStack(final int maxStack, final ObjectType obj) {
        this.maxStack = maxStack;
        this.push(obj);
    }

    /**
     * Returns the size of this OperandStack; that means, how many Type objects there are.
     */
    public int size() {
        return stack.size();
    }

    public ArrayList<Type> getStack() {
        return stack;
    }

    /**
     * Returns the number of f_stack slots this f_stack can hold.
     */
    public int maxStack() {
        return this.maxStack;
    }

    /**
     * Returns the number of f_stack slots used.
     * @see #maxStack()
     */
    public int slotsUsed() {
        /*  XXX change this to a better implementation using a variable
            that keeps track of the actual slotsUsed()-value monitoring
            all push()es and pop()s.
        */
        int slots = 0;
        for (int i=0; i<stack.size(); i++) {
            slots += peek(i).getSize();
        }
        return slots;
    }

    // region f_stack operation
    /**
     * Pushes a Type object onto the f_stack.
     */
    public void push(final Type type) {
        if (type == null) {
            throw new RuntimeException("Cannot push NULL onto OperandStack.");
        }
        if (type == TypeUtils.BOOLEAN || type == TypeUtils.CHAR || type == TypeUtils.BYTE || type == TypeUtils.SHORT) {
            throw new RuntimeException("The OperandStack does not know about '"+type+"'; use Type.INT instead.");
        }
        if (slotsUsed() >= maxStack) {
            throw new RuntimeException("OperandStack too small, should have thrown proper Exception elsewhere. Stack: "+this);
        }
        stack.add(type);
    }

    /**
     * Returns the element on top of the f_stack. The element is popped off the f_stack.
     */
    public Type pop() {
        final Type e = stack.remove(size()-1);
        return e;
    }

    /**
     * Pops i elements off the f_stack. ALWAYS RETURNS "null"!!!
     */
    public Type pop(final int i) {
        for (int j=0; j<i; j++) {
            pop();
        }
        return null;
    }

    /**
     * Returns the element on top of the f_stack. The element is not popped off the f_stack!
     */
    public Type peek() {
        return peek(0);
    }

    /**
     * Returns the element that's i elements below the top element; that means,
     * iff i==0 the top element is returned. The element is not popped off the f_stack!
     */
    public Type peek(final int i) {
        return stack.get(size()-i-1);
    }

    /**
     * Returns true IFF this OperandStack is empty.
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Clears the f_stack.
     */
    public void clear() {
        stack = new ArrayList<>();
    }
    // endregion

    /**
     * Replaces all occurences of u in this OperandStack instance
     * with an "initialized" ObjectType.
     */
    public void initializeObject(final UninitializedObjectType u) {
        for (int i=0; i<stack.size(); i++) {
            if (stack.get(i) == u) {
                stack.set(i, u.getInitialized());
            }
        }
    }

    // region clone
    /**
     * Returns a deep copy of this object; that means, the clone operates
     * on a new f_stack. However, the Type objects on the f_stack are
     * shared.
     */
    @Override
    public Object clone() {
        final OperandStack newstack = new OperandStack(this.maxStack);
        @SuppressWarnings("unchecked") // OK because this.f_stack is the same type
        final ArrayList<Type> clone = (ArrayList<Type>) this.stack.clone();
        newstack.stack = clone;
        return newstack;
    }

    /**
     * Returns a (typed!) clone of this.
     *
     * @see #clone()
     */
    public OperandStack getClone() {
        return (OperandStack) this.clone();
    }
    // endregion

    // region hashcode and equals
    /** @return a hash code value for the object.
     */
    @Override
    public int hashCode() { return stack.hashCode(); }

    /**
     * Returns true if and only if this OperandStack
     * equals another, meaning equal lengths and equal
     * objects on the stacks.
     */
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof OperandStack)) {
            return false;
        }
        final OperandStack s = (OperandStack) o;
        return this.stack.equals(s.stack);
    }
    // endregion

    /**
     * Returns a String representation of this OperandStack instance.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Slots used: ");
        sb.append(slotsUsed());
        sb.append(" MaxStack: ");
        sb.append(maxStack);
        sb.append(".\n");
        for (int i=0; i<size(); i++) {
            sb.append(peek(i));
            sb.append(" (Size: ");
            sb.append(String.valueOf(peek(i).getSize()));
            sb.append(")\n");
        }
        return sb.toString();
    }
}
