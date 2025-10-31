package core.basesyntax.generator;

import java.util.Map;

public interface ReportGenerator {
    void generateReport(Map<String, Integer> storage, String filePath);
}
