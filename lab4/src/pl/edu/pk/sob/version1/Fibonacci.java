package pl.edu.pk.sob.version1;

import java.math.BigInteger;

public class Fibonacci {
    public static BigInteger fib(int i) {
        long f = 0;
        long g = 1;

        for (int j = 1; j <= i; j++) {
            f = f + g;
            g = f - g;
        }

//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException ignored) { }
//        System.err.println(1);
        return BigInteger.valueOf(f);
    }
}
