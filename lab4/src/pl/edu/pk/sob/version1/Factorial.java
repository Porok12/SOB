package pl.edu.pk.sob.version1;

import java.math.BigInteger;

public class Factorial {
    public static BigInteger fact(long number) {
        BigInteger result = BigInteger.valueOf(1);

        for (long factor = 2; factor <= number; factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
        }

        return result;
    }
}
