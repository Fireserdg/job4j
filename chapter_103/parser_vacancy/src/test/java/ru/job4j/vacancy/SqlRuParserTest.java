package ru.job4j.vacancy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quartz.SchedulerException;
import ru.job4j.vacancy.config.Config;
import ru.job4j.vacancy.exception.NoCorrectlyArgumentException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SqlRuParser class test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */
public class SqlRuParserTest {

    /**
     * Contains config
     *
     */
    private Config config;

    /**
     * Create object for test
     *
     */
    @Before
    public void createTestObject() {
        config = new Config();
        config.loadConfig("app.properties");
    }

    /**
     * Drop table after test
     *
     * @throws SQLException exception
     */
    @After
    public void dropTableToDatabase() throws SQLException, ClassNotFoundException {
        Class.forName(config.getValue("jdbc.driver"));
        try (Connection conn = DriverManager.getConnection(config.getValue(
                "jdbc.url"), config.getValue("jdbc.username"),
                config.getValue("jdbc.password"));
             Statement st = conn.createStatement()) {
            st.execute(config.getValue("jdbc.drop"));
        }
    }
    @Test
    public void whenStartSqlParserThenGetResult() throws SchedulerException {
        String[] prop = {"app.properties"};
        SqlRuParser.main(prop);
    }

    @Test(expected = NoCorrectlyArgumentException.class)
    public void whenPassTheWrongArgument() throws SchedulerException {
        String[] prop = {"app.properties", "123"};
        SqlRuParser.main(prop);
    }
}