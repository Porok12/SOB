package pl.edu.pk.sob;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

import static pl.edu.pk.sob.Utils.getUniqueResult;
import static pl.edu.pk.sob.Utils.registerResult;

public class Main {
    public static void fibonacci(int n) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<BigInteger> completionService =
                new ExecutorCompletionService<>(executor);

        completionService.submit(() -> pl.edu.pk.sob.version1.Fibonacci.fib(n));
        completionService.submit(() -> pl.edu.pk.sob.version2.Fibonacci.fib(n));
        completionService.submit(() -> pl.edu.pk.sob.version3.Fibonacci.fib(n));

        Map<BigInteger, Integer> fibonacci = new ConcurrentHashMap<>();
        int todo = 3;
        while(todo > 0) {
            try {
                Future<BigInteger> resultFuture = completionService.take();
                registerResult(fibonacci, resultFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            todo--;
        }

        System.out.println(fibonacci.toString());
        Optional<BigInteger> fib = getUniqueResult(fibonacci);
        if (fib.isPresent()) {
            System.out.println(fib.get());
        } else {
            System.err.println(":(");
        }

        executor.shutdown();
    }

    public static void factorial(int n) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<FutureTask<BigInteger>> tasks = new ArrayList<>(3);
        tasks.add(new FutureTask<>(() -> pl.edu.pk.sob.version1.Factorial.fact(n)));
        tasks.add(new FutureTask<>(() -> pl.edu.pk.sob.version2.Factorial.fact(n)));
        tasks.add(new FutureTask<>(() -> pl.edu.pk.sob.version3.Factorial.fact(n)));
        tasks.forEach(executor::execute);


        Map<BigInteger, Integer> factorials = new ConcurrentHashMap<>();
        tasks.forEach(task -> {
            try {
                registerResult(factorials, task.get(10, TimeUnit.SECONDS));
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        });

        System.out.println(factorials.toString());
        Optional<BigInteger> fac = getUniqueResult(factorials);
        if (fac.isPresent()) {
            System.out.println(fac.get());
        } else {
            System.err.println(":(");
        }

        executor.shutdown();
    }

    public static void main(String[] args) {
        final int n = 2;
        fibonacci(n);
        factorial(n);
    }
}
