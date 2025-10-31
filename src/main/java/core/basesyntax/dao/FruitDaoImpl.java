package core.basesyntax.dao;

import core.basesyntax.db.FruitStorage;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public class FruitDaoImpl implements FruitDao {
    private final Map<String, Integer> fruitStorage = FruitStorage.getStorage();

    @Override
    public void add(String fruitName, int quantity,
                    BiFunction<Integer, Integer, Integer> operation) {
        fruitStorage.compute(fruitName, (k, oldValue) -> {
            int current = oldValue == null ? 0 : oldValue;
            int result = operation.apply(current, quantity);

            if (result < 0) {
                throw new RuntimeException("Not enough " + fruitName + " in storage!");
            }

            return result;
        });
    }

    @Override
    public Integer get(String fruitName) {
        if (fruitStorage.get(fruitName) != null) {
            return fruitStorage.get(fruitName);
        } else {
            throw new NoSuchElementException("No such element: " + fruitName);
        }

    }

    @Override
    public Map<String, Integer> getAll() {
        return new HashMap<>(fruitStorage);
    }
}
