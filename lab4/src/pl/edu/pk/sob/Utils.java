package pl.edu.pk.sob;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

public class Utils {
    public static void registerResult(Map<BigInteger, Integer> map, BigInteger key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    public static Optional<BigInteger> getUniqueResult(Map<BigInteger, Integer> map) {
        Optional<Map.Entry<BigInteger, Integer>> maxEntry = map.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));
        if (maxEntry.isPresent() && map.entrySet()
                .stream()
                .filter(entry -> entry.getValue()
                        .equals(maxEntry.get()
                                .getValue())).count() == 1) {
            return Optional.of(maxEntry.get().getKey());
        } else {
            return Optional.empty();
        }
    }
}
