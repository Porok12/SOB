package pl.edu.pk.sob.version2;

import java.math.BigInteger;

public class Factorial {
    private static BigInteger factorial(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return n;
        } else {
            return factorial(n.subtract(BigInteger.ONE)).multiply(n);
        }
    }

    public static BigInteger fact(int n) {

        return factorial(BigInteger.valueOf(n));
    }
}
