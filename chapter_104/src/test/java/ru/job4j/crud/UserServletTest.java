package ru.job4j.crud;

import org.junit.After;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * Test when working with multithreading
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 10.11.18
 */
public class UserServletTest {

    /**
     * Init Config for drop database.
     *
     */
    private static final Config CONF = Config.getInstance();

    /**
     * Create connection to database for drop database tracker.
     *
     */
    @After
    public void dropDb() {
        try (final Connection connection = DriverManager.getConnection(CONF.getValue("get.url"),
                CONF.getValue("get.name"), CONF.getValue("get.password"));
             final Statement statement = connection.createStatement()) {
            statement.execute(CONF.getValue("get.dropTable"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = UserNotFoundException.class)
    public void whenOperationByUsersThenGetResult() throws InterruptedException {
        ValidateService val = ValidateService.getInstance();
        DispatchPattern dispatch = DispatchPattern.getInstance();
        for (int i = 1; i < 300; i++) {
            int count = i;
            new Thread(() -> dispatch.
                        init().sent(() -> Arrays.asList("add",
                    String.format("User%s", count),
                    String.format("login.%s", count),
                    String.format("email%s@.gmail.com", count)))
            ).start();
        }
        Thread.sleep(500);
        int resultSize = val.findAll().size();
        int expectedSize = 299;
        for (int i = 5; i < 30; i++) {
            int countUp = i;
            int countDel = i + 25;
            new Thread(() -> dispatch.init().sent(
                    () -> Arrays.asList("update",
                    String.valueOf(countUp),
                    String.format("UpdateUser%s", countUp),
                    String.format("Updatelogin%s", countUp),
                    String.format("UpdateEmail%s", countUp)))
            ).start();
            new Thread(() -> dispatch.init().sent(() -> Arrays.asList(
                    "delete", String.valueOf(countDel)))
            ).start();

        }
        Thread.sleep(500);
        val.findAll().forEach(System.out::println);
        assertThat(resultSize, is(expectedSize));
        assertThat(val.findById("5").getName(), is("UpdateUser5"));
        assertThat(val.findById("14").getName(), is("UpdateUser14"));
        assertNull(val.findById("30"));
        assertNull(val.findById("49"));
        assertThat(val.findAll().size(), is(274));
        assertThat(dispatch.init().sent(
                () -> Arrays.asList("add", "Jon", "login", "email")),
                is(String.format(Message.MSG_ADD, "Jon")));
        assertThat(dispatch.init().sent(
                () -> Arrays.asList("update", "1", "Bill", "", "login")),
                is(String.format(Message.MSG_UPDATE, "1")));
        assertThat(dispatch.init().sent(
                () -> Arrays.asList("delete", "2")),
                is(String.format(Message.MSG_DELETE, "2")));
        dispatch.init().sent(() -> Arrays.asList("delete", "37"));
    }
}