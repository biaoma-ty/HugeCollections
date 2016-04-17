package oversizecollections.primitives;

/**
 * This class will generate an easily usable array that is larger than the maximum allowable
 * array in the JVM. (Roughly Integer.MAX_VALUE - 8, slightly higher in some versions.)
 *
 * Indexes are longs (64-bit), elements are bits (1-bit) compressed into bytes (8-bit.
 *
 * @author Ellen Hebert
 */
public final class OversizeBitArray {

    /**
     * The size of the OversizeByteArray in terms of array elements.
     * Specified when calling constructor.
     */
    private long size;

    /**
     * The 'segments' of the OversizeByteArray. Since there is a cap on the size of a single
     * array, this class makes a number of separate arrays then does the math to make them
     * appear to be one externally.
     */
    private byte[][] segments;

    public static void main(String[] args) {
        OversizeBitArray oversizeBitArray = new OversizeBitArray(10);
        oversizeBitArray.set(10, true);
        oversizeBitArray.set(11, false);
        oversizeBitArray.set(12, true);

        System.out.println(oversizeBitArray.get(10));
        System.out.println(oversizeBitArray.get(11));
        System.out.println(oversizeBitArray.get(12));

        oversizeBitArray.set(11, true);
        oversizeBitArray.set(12, false);

        System.out.println(oversizeBitArray.get(11));
        System.out.println(oversizeBitArray.get(12));

        System.out.println("Scan");

        for (int i = 0; i < 80; i++) {
            System.out.println(oversizeBitArray.get(i));
        }
    }

    /**
     * Biggest array size guaranteed to work across all JVMs.
     */
    private static final int MAX_ARR_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Constructs a OversizeByteArray.
     * @param size the number of array elements
     */
    public OversizeBitArray(final long size) {
        this.size = (long) Math.ceil(size / 8);

        int segmentCount = (int) (size / MAX_ARR_SIZE) + 1;
        segments = new byte[segmentCount][];

        for (int i = 0; i < segmentCount - 1; i++) {
            segments[i] = new byte[MAX_ARR_SIZE];
        }

        segments[segmentCount - 1] = new byte[(int) (size - (MAX_ARR_SIZE * (segmentCount - 1)))];
    }

    /**
     * Returns the integer stored at a supplied array index.
     * @param index the index of the element to be returned
     * @return the element at the supplied index
     */
    public boolean get(long index) {
        long segmentedIndex = (long) Math.floor(index / 8);

        long segment = segmentedIndex / MAX_ARR_SIZE;
        long offset = segmentedIndex % MAX_ARR_SIZE;
        byte cache =  segments[((int) segment)][((int) offset)];

        return ((cache >> index % 8) & 1) == 1;
    }

    /**
     * Sets a value in the array.
     * @param index the index which will be the set to the value
     * @param value the value to be set at the index
     */
    public void set(long index, boolean value) {
        long segmentedIndex = index / 8;
        long segment = segmentedIndex / MAX_ARR_SIZE;
        long offset = segmentedIndex % MAX_ARR_SIZE;

        if(value) {
            byte place = (byte) (index % 8);
            segments[((int) segment)][((int) offset)] = (byte) (segments[((int) segment)][((int) offset)] & (byte) (1 << place));
        } else {
            byte place = (byte) (index % 8);
            segments[((int) segment)][((int) offset)] = (byte) (segments[((int) segment)][((int) offset)] & (byte) ~(1 << place));
        }
    }

    /**
     * The number of elements that can be stored in this OversizeByteArray.
     */
    public long getSize() {
        return size;
    }
}
