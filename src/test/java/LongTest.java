import hugecollections.primitives.OversizeBooleanArray;
import hugecollections.structures.OversizeStack;

/**
 * -d64 -Xmx16G
 */
public class LongTest {

    public static void main(String[] args) {

        OversizeStack<Byte> oversizeStack = new OversizeStack<Byte>(Integer.MAX_VALUE + 1l);

        for (long i = 0; i < 100; i++) {
            oversizeStack.push((byte) (i % 256));

            if(i % 1000 == 0) {
                System.out.println(oversizeStack.peek());
            }
        }

        System.out.println("Done pushing");

        System.out.println(oversizeStack.pop());
        System.out.println(oversizeStack.pop());
        System.out.println(oversizeStack.pop());
        System.out.println(oversizeStack.pop());
        System.out.println(oversizeStack.pop());
        System.out.println(oversizeStack.pop());
    }
}
