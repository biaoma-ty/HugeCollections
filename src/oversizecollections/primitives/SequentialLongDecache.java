package oversizecollections.primitives;

import java.io.File;
import java.util.UUID;

public class SequentialLongDecache {

    private UUID loadingFor;
    private long maxIndex;
    private long place = 0;
    private long iteration;
    private long capacity;
    private OversizeLongArray oversizeLongArray;
    private int placeNumber = -1;

    public SequentialLongDecache(int number) {
        this.placeNumber = number;
    }

    public SequentialLongDecache(UUID precursor, long maxIndex, long capacity) {
        this.loadingFor = precursor;
        this.maxIndex = maxIndex;
        this.capacity = capacity;
        this.iteration = -1;
    }

    public long get() {
        if(placeNumber!=-1) return placeNumber;
        return load(place++);
    }

    private long load(long index) {
        long indexTargeted = index / capacity;

        if(indexTargeted == iteration) {
            return oversizeLongArray.get(index % capacity);
        } else {
            iteration++;
            oversizeLongArray = LongArrayIO.load(new File(loadingFor + " - " + iteration), capacity / OversizeLongArray.MAX_ARR_SIZE);
            return oversizeLongArray.get(index % capacity);
        }
    }

    public long getMaxIndex() {
        return maxIndex;
    }

    public UUID getLoadingFor() {
        return loadingFor;
    }

    public long size() {
        if(placeNumber != -1) return 1;
        else return maxIndex;
    }
}
