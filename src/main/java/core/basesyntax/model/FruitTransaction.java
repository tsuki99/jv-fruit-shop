package core.basesyntax.model;

import java.util.Objects;

public class FruitTransaction {
    private Type operationType;
    private String fruitName;
    private int quantity;

    public FruitTransaction(Type operationType, String fruitName, int quantity) {
        this.operationType = operationType;
        this.fruitName = fruitName;
        this.quantity = quantity;
    }

    public Type getOperationType() {
        return operationType;
    }

    public FruitTransaction setOperationType(Type operationType) {
        this.operationType = operationType;
        return this;
    }

    public String getFruitName() {
        return fruitName;
    }

    public FruitTransaction setFruitName(String fruitName) {
        this.fruitName = fruitName;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public FruitTransaction setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public enum Type {
        BALANCE("b"),
        SUPPLY("s"),
        PURCHASE("p"),
        RETURN("r");

        private String code;

        Type(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FruitTransaction fruitTransaction = (FruitTransaction) o;
        return quantity == fruitTransaction.quantity
                && operationType == fruitTransaction.operationType
                && Objects.equals(fruitName, fruitTransaction.fruitName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationType, fruitName, quantity);
    }

    @Override
    public String toString() {
        return "FruitTransaction{"
                + "operationType=" + operationType
                + ", fruitName='" + fruitName + '\''
                + ", quantity=" + quantity
                + '}';
    }
}
