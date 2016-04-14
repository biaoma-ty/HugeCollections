package oversizecollections.primitives;

/**
 * This class will generate an easily usable array that is larger than the maximum allowable
 * array in the JVM. (Roughly Integer.MAX_VALUE - 8, slightly higher in some versions.)
 *
 * Indexes are longs (64-bit), elements are booleans (8-bit).
 *
 * @author Ellen Hebert
 */
public final class OversizeBooleanArray {

    /**
     * The size of the OversizeBooleanArray in terms of array elements.
     * Specified when calling constructor.
     */
    private long size;

    /**
     * The 'segments' of the OversizeBooleanArray. Since there is a cap on the size of a single
     * array, this class makes a number of separate arrays then does the math to make them
     * appear to be one externally.
     */
    private boolean[][] segments;

    /**
     * Biggest array size guaranteed to work across all JVMs.
     */
    private static final int MAX_ARR_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Constructs a OversizeBooleanArray.
     * @param size the number of array elements
     */
    public OversizeBooleanArray(final long size) {
        this.size = size;

        int segmentCount = (int) (size / MAX_ARR_SIZE) + 1;
        segments = new boolean[segmentCount][];

        for (int i = 0; i < segmentCount - 1; i++) {
            segments[i] = new boolean[MAX_ARR_SIZE];
        }

        segments[segmentCount - 1] = new boolean[(int) (size - (MAX_ARR_SIZE * (segmentCount - 1)))];
    }

    /**
     * Returns the integer stored at a supplied array index.
     * @param index the index of the element to be returned
     * @return the element at the supplied index
     */
    public boolean get(long index) {
        long segment = index / MAX_ARR_SIZE;
        long offset = index % MAX_ARR_SIZE;
        return segments[((int) segment)][((int) offset)];
    }

    /**
     * Sets a value in the array.
     * @param index the index which will be the set to the value
     * @param value the value to be set at the index
     */
    public void set(long index, boolean value) {
        long segment = index / MAX_ARR_SIZE;
        long offset = index % MAX_ARR_SIZE;
        segments[((int) segment)][((int) offset)] = value;
    }

    /**
     * The number of elements that can be stored in this OversizeBooleanArray.
     */
    public long getSize() {
        return size;
    }
}
