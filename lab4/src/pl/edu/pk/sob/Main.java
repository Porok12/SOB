package pl.edu.pk.sob;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;
import static pl.edu.pk.sob.Utils.convertToInteger;
import static pl.edu.pk.sob.Utils.getUniqueResult;
import static pl.edu.pk.sob.Utils.getResults;

public class Main {
    private static final int MODELES_AMOUNT = 3;

    public static void fibonacci(int n) throws InterruptedException {
        Map<BigInteger, Integer> fibonacci = new HashMap<>();
        List<FutureTask<BigInteger>> tasks = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(MODELES_AMOUNT);
        tasks.add(new FutureTask<>(() -> pl.edu.pk.sob.version1.Fibonacci.fib(n)));
        tasks.add(new FutureTask<>(() -> pl.edu.pk.sob.version2.Fibonacci.fib(n)));
        tasks.add(new FutureTask<>(() -> pl.edu.pk.sob.version3.Fibonacci.fib(n)));
        tasks.forEach(executor::submit);

        getResults(fibonacci, tasks, 1);

        Thread.sleep(20);

        System.out.println(fibonacci.toString());
        Optional<BigInteger> fib = getUniqueResult(fibonacci, MODELES_AMOUNT);
        if (fib.isPresent()) {
            System.out.println("Wynik to " + fib.get());
        } else {
            System.err.println("Nie uzyskano spójnego wyniku");
        }

        executor.shutdown();
    }

    public static void factorial(int n) throws InterruptedException {
        Map<BigInteger, Integer> factorials = new HashMap<>();
        List<FutureTask<BigInteger>> tasks = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(MODELES_AMOUNT);
        tasks.add(new FutureTask<>(() -> pl.edu.pk.sob.version1.Factorial.fact(n)));
        tasks.add(new FutureTask<>(() -> pl.edu.pk.sob.version2.Factorial.fact(n)));
        tasks.add(new FutureTask<>(() -> pl.edu.pk.sob.version3.Factorial.fact(n)));
        tasks.forEach(executor::submit);

        getResults(factorials, tasks, 1);

        Thread.sleep(20);

        System.out.println(factorials.toString());
        Optional<BigInteger> fac = getUniqueResult(factorials, MODELES_AMOUNT);
        if (fac.isPresent()) {
            System.out.println("Wynik to " + fac.get());
        } else {
            System.err.println("Nie uzyskano spójnego wyniku");
        }

        executor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        Integer fibonacci, factorial;

        if (args.length > 0 && (fibonacci = convertToInteger(args[0])) != null) {
            System.out.println(String.format("--Fibonacci (n=%d)--", fibonacci));
            fibonacci(fibonacci);
        }

        Thread.sleep(20);

        if (args.length > 1 && (factorial = convertToInteger(args[1])) != null) {
            System.out.println(String.format("\n--Silnia (n=%d)--", factorial));
            factorial(factorial);
        }
    }
}
