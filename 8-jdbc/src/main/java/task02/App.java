package task02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import task02.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class App {
    public static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        Scanner scanner = new Scanner(System.in);
        log.info("Type count of tables");
        int countOfTables = scanner.nextInt();
        log.info("Type count of columns");
        int countOfColumns = scanner.nextInt();
        log.info("Type count of rows");
        int countOfRows = scanner.nextInt();
        for (int i = 0; i < countOfTables; i++) {
            String tableName = createAndFillTable(countOfColumns, countOfRows, jdbcTemplate);
            log.info("Table {} has been initialized successfully", tableName);
        }
        log.info("Finish");

    }

    private static String createAndFillTable(int countOfColumns, int countOfRows, JdbcTemplate jdbcTemplate) {
        String tableName = RandomGenerator.getRandomString();
        List<String> currentTypes = new ArrayList<>();
        StringBuilder createFormat = new StringBuilder("CREATE TABLE %s (");
        for (int i = 0; i < countOfColumns; i++) {
            String currentColumnName = RandomGenerator.getRandomString();
            String currentType = RandomGenerator.getRandomType();
            currentTypes.add(currentType);
            createFormat.append(currentColumnName).append(" ").append(currentType).append(",");
        }
        createFormat.deleteCharAt(createFormat.length() - 1);
        createFormat.append(")");

        String create = String.format(createFormat.toString(), tableName);
        jdbcTemplate.execute(create);
        log.info("INSERT DATA TO TABLE {}", tableName);
        fillTable(tableName, currentTypes, countOfRows, countOfColumns, jdbcTemplate);
        return tableName;
    }

    private static void fillTable(String tableName, List<String> currentTypes, int countOfRows, int countOfColumns, JdbcTemplate jdbcTemplate) {
        StringBuilder insertFormatBuilder = new StringBuilder("INSERT INTO " + tableName + " values (");
        for (int i = 0; i < countOfColumns; i++) {
            insertFormatBuilder.append("%s,");
        }
        insertFormatBuilder.deleteCharAt(insertFormatBuilder.length() - 1);
        insertFormatBuilder.append(")");
        String insertFormat = insertFormatBuilder.toString();
        IntStream.range(0, countOfRows)
                .parallel()
                .forEach(value -> {
                    String[] arguments = new String[countOfColumns];
                    for (int j = 0; j < countOfColumns; j++) {
                        String currentType = currentTypes.get(j);
                        arguments[j] = RandomGenerator.getValue(currentType);
                    }
                    String insert = String.format(insertFormat, (Object[]) arguments);
                    log.info("Insert statement: {}", insert);
                    jdbcTemplate.execute(insert);
                });
    }

    private static JdbcTemplate getJdbcTemplate() {
        SingleConnectionDataSource ds = new SingleConnectionDataSource();
        ds.setDriverClassName("org.mariadb.jdbc.Driver");
        ds.setUrl("jdbc:mariadb://localhost:3306/hldb");
        ds.setUsername("root");
        ds.setPassword("123456");
        return new JdbcTemplate(ds);
    }
}