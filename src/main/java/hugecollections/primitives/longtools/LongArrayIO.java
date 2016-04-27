package hugecollections.primitives.longtools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LongArrayIO {

    public static OversizeLongArray load(File file, long segments) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            List<long[]> longArrays = new ArrayList<long[]>();

            for (int i = 0; i < segments; i++) {
                try {
                    objectInputStream.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            return new OversizeLongArray(longArrays);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void save(OversizeLongArray oversizeLongArray, File file) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));

            for(long[] longSeg : oversizeLongArray.getSegments()) {
                objectOutputStream.writeObject(longSeg);
            }

            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
