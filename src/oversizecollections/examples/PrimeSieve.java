package oversizecollections.examples;

import oversizecollections.primitives.OversizeBooleanArray;

import java.text.NumberFormat;

/**
 * Prime sieve
 *
 * Primes under 10 billion by default
 */
public class PrimeSieve {

    public static void main(String[] args) {
        long primeCap = 10000000000l;
        long primesFound = 0;

        OversizeBooleanArray boolArray = new OversizeBooleanArray((long) (primeCap * 1.01));

        for (int i = 2; i < primeCap; i++) {
            if(!boolArray.get(i)) {
                primesFound++;

                // Is prime
                for (long k = 1; k < Math.ceil(primeCap / i) + 1; k++) {
                    boolArray.set(k * i, true);
                }
            }
        }

        NumberFormat numberFormat = NumberFormat.getInstance();
        System.out.println(numberFormat.format(primesFound) + " primes below " + numberFormat.format(primeCap));
    }
}
