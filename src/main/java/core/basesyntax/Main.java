package core.basesyntax;

import core.basesyntax.dao.FruitDao;
import core.basesyntax.dao.FruitDaoImpl;
import core.basesyntax.generator.ReportGenerator;
import core.basesyntax.generator.ReportGeneratorImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.operation.BalanceOperationHandler;
import core.basesyntax.operation.OperationHandler;
import core.basesyntax.operation.PurchaseOperationHandler;
import core.basesyntax.operation.ReturnOperationHandler;
import core.basesyntax.operation.SupplyOperationHandler;
import core.basesyntax.service.ReaderService;
import core.basesyntax.service.ReaderServiceImpl;
import core.basesyntax.service.ShopService;
import core.basesyntax.service.ShopServiceImpl;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FruitDao fruitDao = new FruitDaoImpl();

        Map<FruitTransaction.Type, OperationHandler> operationHandlerMap = new HashMap<>();
        operationHandlerMap.put(FruitTransaction.Type.BALANCE,
                new BalanceOperationHandler(fruitDao));
        operationHandlerMap.put(FruitTransaction.Type.PURCHASE,
                new PurchaseOperationHandler(fruitDao));
        operationHandlerMap.put(FruitTransaction.Type.SUPPLY,
                new SupplyOperationHandler(fruitDao));
        operationHandlerMap.put(FruitTransaction.Type.RETURN,
                new ReturnOperationHandler(fruitDao));

        OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlerMap);

        ReaderService readerService = new ReaderServiceImpl();
        List<String> fruitData = readerService.read("src/main/resources/inputfile.csv");

        ShopService shopService = new ShopServiceImpl(operationStrategy);
        shopService.process(fruitData);

        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        reportGenerator.generateReport(fruitDao.getAll(), "src/main/resources/finalReport.csv");
    }
}
