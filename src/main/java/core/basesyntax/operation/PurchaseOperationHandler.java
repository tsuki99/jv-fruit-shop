package core.basesyntax.operation;

import core.basesyntax.dao.FruitDao;
import core.basesyntax.model.FruitTransaction;

public class PurchaseOperationHandler implements OperationHandler {
    private final FruitDao fruitDao;

    public PurchaseOperationHandler(FruitDao fruitDao) {
        this.fruitDao = fruitDao;
    }

    @Override
    public void pushToStorage(FruitTransaction transaction) {
        fruitDao.add(transaction.getFruitName(), transaction.getQuantity(),
                (oldValue, newValue) -> {
                int result = oldValue - newValue;

                if (result < 0) {
                        throw new RuntimeException("Not enough "
                        + transaction.getFruitName() + " in storage!");
                }

                return result;
            });
    }
}
