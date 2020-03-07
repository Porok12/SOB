package pl.edu.pk.sob.version3;

import java.math.BigInteger;
import java.util.Random;

public class Fibonacci {
    private static final int MAX = 2000;
    private static BigInteger f[] = new BigInteger[MAX];

    // Returns n'th fibonacci number using
    // table f[]
    public static BigInteger fib(int n) {
        if (MAX < n || 0 > n) {
            return BigInteger.valueOf(new Random().nextLong());
        }

        // Base cases
        if (n == 0) {
            return BigInteger.ZERO;
        }

        if (n == 1 || n == 2) {
            return (f[n] = BigInteger.ONE);
        }

        // If fib(n) is already computed
        if (f[n] != null) {
            return f[n];
        }

        int k = (n & 1) == 1? (n + 1) / 2: n / 2;

        // Applyting above formula [Note value
        // n&1 is 1 if n is odd, else 0.
        f[n] = (n & 1) == 1
                ? (fib(k)
                    .multiply(fib(k))
                    .add(fib(k - 1)
                    .multiply(fib(k - 1))))
                : (fib(k - 1)
                    .multiply(BigInteger.valueOf(2L))
                    .add(fib(k)))
                    .multiply(fib(k));

        return f[n];
    }
}
