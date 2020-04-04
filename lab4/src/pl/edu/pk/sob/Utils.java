package pl.edu.pk.sob;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

public class Utils {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void registerResult(Map<BigInteger, Integer> map, BigInteger key) {
        lock.lock();
        Integer value = map.getOrDefault(key, 0);
        map.put(key, value + 1);
        lock.unlock();
    }

    public static Optional<BigInteger> getUniqueResult(Map<BigInteger, Integer> map, Integer total) {
        for (Map.Entry<BigInteger, Integer> entry: map.entrySet()) {
            if (entry.getValue() * 2 >= total) {
                return Optional.of(entry.getKey());
            }
        }

        return Optional.empty();
    }

    public static Integer convertToInteger(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            System.err.println(String.format("Nieudana konwersja '%s' na Integer", value));
            return null;
        }
    }

    public static void getResults(Map<BigInteger, Integer> results, List<FutureTask<BigInteger>> tasks, Integer timeout) {
        tasks.parallelStream().forEach(task -> {
            try {
                BigInteger result = task.get(timeout, TimeUnit.MILLISECONDS);
                registerResult(results, result);
                System.out.println("Wynik zadania: " + result);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                task.cancel(true);
                System.out.println("Zakończono przedwcześnie zadanie");
            }
        });
    }
}
