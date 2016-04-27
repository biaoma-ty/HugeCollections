package hugecollections.structures;

import hugecollections.OversizeArray;

/**
 * 64-bit stack
 */
public class OversizeStack<E> {

    /**
     * Array in which that stack will be stored.
     */
    private OversizeArray<E> oversizeArray;

    /**
     * Index of the place the next item in the stack will be placed.
     */
    private long stackPointer = 0;

    /**
     * @param sizeCap the size of the stack
     */
    public OversizeStack(final long sizeCap) {
        this.oversizeArray = new OversizeArray<E>(sizeCap);
    }

    public void push(E item) {
        oversizeArray.set(stackPointer++, item);
    }

    public E pop() {
        return oversizeArray.get(--stackPointer);
    }

    public E peek() {
        return oversizeArray.get(stackPointer - 1);
    }
}
