/**
 * Array of long size
 */
public final class OversizeArray<E> implements OversizeCollection<E> {

    /**
     * The size of the OversizeArray in terms of array elements.
     * Specified when calling constructor.
     */
    private long size;

    /**
     * The 'segments' of the OversizeArray. Since there is a cap on the size of a single
     * array, this class makes a number of separate arrays then does the math to make them
     * appear to be one externally.
     */
    private E[][] segments;

    /**
     * Biggest array size guaranteed to work across all JVMs.
     */
    private static final int MAX_ARR_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Constructs a OversizeArray.
     * @param size the number of array elements
     */
    public OversizeArray(final long size) {
        this.size = size;

        int segmentCount = (int) (size / MAX_ARR_SIZE) + 1;
        segments = (E[][]) new Object[segmentCount][];

        for (int i = 0; i < segmentCount - 1; i++) {
            segments[i] = (E[]) new Object[MAX_ARR_SIZE];
        }

        segments[segmentCount - 1] = (E[]) new Object[(int) (size - (MAX_ARR_SIZE * (segmentCount - 1)))];
    }

    /**
     * Returns the integer stored at a supplied array index.
     * @param index the index of the element to be returned
     * @return the element at the supplied index
     */
    public E get(long index) {
        long segment = index / MAX_ARR_SIZE;
        long offset = index % MAX_ARR_SIZE;
        return segments[((int) segment)][((int) offset)];
    }

    /**
     * Sets a value in the array.
     * @param index the index which will be the set to the value
     * @param value the value to be set at the index
     */
    public void set(long index, E value) {
        long segment = index / MAX_ARR_SIZE;
        long offset = index % MAX_ARR_SIZE;
        segments[((int) segment)][((int) offset)] = value;
    }

    /**
     * The number of elements that can be stored in this OversizeIntArray.
     */
    @Override
    public long size() {
        return size();
    }
}
