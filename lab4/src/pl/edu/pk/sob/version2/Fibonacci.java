package pl.edu.pk.sob.version2;

import java.math.BigInteger;

public class Fibonacci {
    public static BigInteger fib(int n) {
        BigInteger F[][] = new BigInteger[][]{{BigInteger.ONE,BigInteger.ONE}, {BigInteger.ONE,BigInteger.ZERO}};
        if (n == 0) {
            return BigInteger.ZERO;
        }
        power(F, n - 1);

//        System.err.println(2);
        return F[0][0];
    }

    /* Helper function that multiplies 2 matrices F and M of size 2*2, and
    puts the multiplication result back to F[][] */
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

    /* Helper function that calculates F[][] raise to the power n and puts the
    result in F[][]
    Note that this function is designed only for fib() and won't work as general
    power function */
    private static void power(BigInteger F[][], int n) {
        int i;
        BigInteger M[][] = new BigInteger[][]{{BigInteger.ONE,BigInteger.ONE},{BigInteger.ONE,BigInteger.ZERO}};

        // n - 1 times multiply the matrix to {{1,0},{0,1}}
        for (i = 2; i <= n; i++)
            multiply(F, M);
    }
}
