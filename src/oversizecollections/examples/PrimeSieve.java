package oversizecollections.examples;

import oversizecollections.primitives.OversizeBooleanArray;

import java.text.NumberFormat;

/**
 * Prime sieve
 *
 * Primes under 5 billion by default
 */
public class PrimeSieve {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long primeCap = 5000000000l;
        long primesFound = getPrimesBelow(primeCap);
        NumberFormat numberFormat = NumberFormat.getInstance();
        System.out.println(numberFormat.format(primesFound) + " primes below " + numberFormat.format(primeCap));
        System.out.println("Computation finished in: " + numberFormat.format(System.currentTimeMillis() - startTime) + "ms");
    }

    public static long getPrimesBelow(long primeCap) {
        // Set to one because the prime '2' will not be found by the algorithm. It is pre-marked as prime.
        long primesFound = 1;

        OversizeBooleanArray boolArray = new OversizeBooleanArray((long) (primeCap * 1.1));

        for (long i = 0; i < Math.ceil(primeCap / 2); i++) {
            boolArray.set(i * 2, true);
        }

        for (long i = 3; i < primeCap; i+=2) {
            if(!boolArray.get(i)) {
                primesFound++;

                // Is prime
                for (long k = 1; k < Math.ceil(primeCap / i) + 1; k++) {
                    boolArray.set(k * i, true);
                }
            }
        }

        return primesFound;
    }
}
