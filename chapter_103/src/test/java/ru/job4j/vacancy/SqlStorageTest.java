package ru.job4j.vacancy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SqlStorage class test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */
public class SqlStorageTest {

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
    public void dropTableToDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection(config.getValue("jdbc.url"),
                config.getValue("jdbc.username"), config.getValue("jdbc.password"));
             Statement st = conn.createStatement()) {
            st.execute(config.getValue("jdbc.drop"));
        }
    }

    @Test
    public void whenAddVacancyToDB() throws ClassNotFoundException, SQLException {
        Class.forName(config.getValue("jdbc.driver"));
        SqlStorage sqlStorage = new SqlStorage(config);
        sqlStorage.addVacancy();
        List<Item> result = new ArrayList<>();
        String sqlQuery = config.getValue("jdbc.testQuery");
        try (Connection conn = DriverManager.getConnection(config.getValue("jdbc.url"),
                config.getValue("jdbc.username"), config.getValue("jdbc.password"));
             Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sqlQuery)) {
            while (rs.next()) {
                Item item = new Item(rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4));
                item.setId(rs.getInt(1));
                result.add(item);
            }
        }
        String firstName = "Java/BackEnd/Middle Мск/Офис/FullTime (150) [new]";
        String firstUrl = "http://www.sql.ru/forum/1281928/java-backend-middle-msk-ofis-fulltime-150";
        Timestamp firstCreate = Timestamp.valueOf(LocalDateTime.parse("2018-01-08T14:43"));
        Item first = new Item(firstName, firstUrl, firstCreate);
        first.setId(1);
        String secondName = "Java Developer, СПб/Мск/Долгопрудный, ЗП до 200k net [new]";
        String secondUrl = "http://www.sql.ru/forum/1282020/java-developer-spb-msk-dolgoprudnyy-zp-do-200k-net";
        Timestamp secondCreate = Timestamp.valueOf(LocalDateTime.parse("2018-01-09T15:41"));
        Item second = new Item(secondName, secondUrl, secondCreate);
        second.setId(2);
        List<Item> expect = Arrays.asList(first, second);
        assertThat(result, is(expect));
    }
}