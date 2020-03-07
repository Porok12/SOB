package pl.edu.pk.sob;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static pl.edu.pk.sob.Utils.getUniqueResult;
import static pl.edu.pk.sob.Utils.registerResult;

public class Main {
    public static void main(String[] args) {
        int n = 3;

        Map<BigInteger, Integer> fibonacci = new HashMap<>();
        registerResult(fibonacci, pl.edu.pk.sob.version1.Fibonacci.fib(n));
        registerResult(fibonacci, pl.edu.pk.sob.version2.Fibonacci.fib(n));
        registerResult(fibonacci, pl.edu.pk.sob.version3.Fibonacci.fib(n));
        System.out.println(fibonacci.toString());
        Optional<BigInteger> fib = getUniqueResult(fibonacci);
        if (fib.isPresent()) {
            System.out.println(fib.get());
        } else {
            System.err.println(":(");
        }

        n = 3;

        Map<BigInteger, Integer> factorials = new HashMap<>();
        registerResult(factorials, pl.edu.pk.sob.version1.Factorial.fact(n));
        registerResult(factorials, pl.edu.pk.sob.version2.Factorial.fact(n));
        registerResult(factorials, pl.edu.pk.sob.version3.Factorial.fact(n));
        System.out.println(factorials.toString());
        Optional<BigInteger> fac = getUniqueResult(factorials);
        if (fac.isPresent()) {
            System.out.println(fac.get());
        } else {
            System.err.println(":(");
        }
    }
}
