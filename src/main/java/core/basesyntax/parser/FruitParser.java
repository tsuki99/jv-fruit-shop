package core.basesyntax.parser;

import core.basesyntax.model.FruitTransaction;
import java.util.Arrays;

public class FruitParser {
    public static FruitTransaction parse(String fruitInfo) {
        String[] parts = fruitInfo.split(",");
        FruitTransaction.Type type = getType(parts[0]);
        String name = parts[1];
        int quantity = Integer.parseInt(parts[2]);
        return new FruitTransaction(type, name, quantity);
    }

    private static FruitTransaction.Type getType(String code) {
        return Arrays.stream(FruitTransaction.Type.values())
                .filter(t -> t.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown operation code: " + code));
    }
}
