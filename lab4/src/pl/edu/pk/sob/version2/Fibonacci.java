package pl.edu.pk.sob.version2;

import java.math.BigInteger;

public class Fibonacci {
    public static BigInteger fib(int n) {
        BigInteger F[][] = new BigInteger[][]{{BigInteger.ONE,BigInteger.ONE}, {BigInteger.ONE,BigInteger.ZERO}};
        if (n == 0) {
            return BigInteger.ZERO;
        }
        power(F, n - 1);

        return F[0][0];
    }

    private static void multiply(BigInteger F[][], BigInteger M[][]) {
        BigInteger x = F[0][0].multiply(M[0][0]).add(F[0][1].multiply(M[1][0]));
        BigInteger y = F[0][0].multiply(M[0][1]).add(F[0][1].multiply(M[1][1]));
        BigInteger z = F[1][0].multiply(M[0][0]).add(F[1][1].multiply(M[1][0]));
        BigInteger w = F[1][0].multiply(M[0][1]).add(F[1][1].multiply(M[1][1]));

        F[0][0] = x;
        F[0][1] = y;
        F[1][0] = z;
        F[1][1] = w;
    }

    private static void power(BigInteger F[][], int n) {
        int i;
        BigInteger M[][] = new BigInteger[][]{{BigInteger.ONE,BigInteger.ONE},{BigInteger.ONE,BigInteger.ZERO}};

        for (i = 2; i <= n; i++)
            multiply(F, M);
    }
}
