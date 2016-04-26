package oversizecollections.primitives.longtools;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class SequentialLongCache {

    private OversizeLongArray oversizeLongArray;
    private UUID uuid = UUID.randomUUID();
    private long capacity = 0;
    private long index = 0;
    private long iteration = 0;

    public SequentialLongCache(long capacity) {
        this.capacity = capacity;
        oversizeLongArray = new OversizeLongArray(capacity);
    }

    public static void main(String[] args) {
        SequentialLongCache sequentialLongCache = new SequentialLongCache(10000);
        while(true) {
            sequentialLongCache.add(10l);
        }
    }

    public synchronized void add(long l) {
        if(index < capacity) {
            oversizeLongArray.set(index++, l);
        } else {
            File file = new File(uuid + " - " + l / oversizeLongArray.getSize() + ".dat");
            try {
                file.createNewFile();
                LongArrayIO.save(oversizeLongArray, file);
                oversizeLongArray = new OversizeLongArray(capacity);
                index = 0;
                iteration++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void decomission() {
        File file = new File(uuid + " - " + index++ + ".dat");
        try {
            file.createNewFile();
            LongArrayIO.save(oversizeLongArray, file);
        } catch (Exception e) {

        }

    }

    public UUID getUUID() {
        return uuid;
    }

    public long getIteration() {
        return iteration;
    }

    //
//    public synchronized long get(long l) {
//        if(l / capacity == iteration) {
//            oversizeLongArray.get(l % capacity);
//        } else {
//
//        }
//    }

    public long getCapacity() {
        return capacity;
    }
}
