package ru.job4j.crud.store;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.crud.Config;
import ru.job4j.crud.models.Role;
import ru.job4j.crud.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test database using multiple threads.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 23.11.18
 */
public class DbStoreTestMulti {

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

    @Ignore
    public void whenOperationByUsersThenGetResult() throws InterruptedException {
        DbStore db = DbStore.getInstance();
        for (int i = 1; i < 300; i++) {
            int count = i;
            new Thread(() -> db.add(new User(String.valueOf(count),
                    String.format("User%s", count),
                    String.format("login.%s", count),
                    String.format("password.%s", count),
                    String.format("email%s@.gmail.com", count),
                    System.currentTimeMillis(), Role.USER))
            ).start();
        }
        Thread.sleep(1000);
        int resultSize = db.findAll().size();
        int expectedSize = 299;
        for (int i = 5; i < 30; i++) {
            int countUp = i;
            int countDel = i + 25;
            new Thread(() -> db.update(new User(String.valueOf(countUp),
                    String.format("UpdateUser%s", countUp),
                    String.format("UpdateLogin%s", countUp),
                    String.format("UpdatePassword%s", countUp),
                    String.format("UpdateEmail%s@.gmail.com", countUp),
                    db.findById(String.valueOf(countUp)).getCreate(), Role.USER))
            ).start();
            new Thread(() -> db.delete(String.valueOf(countDel))).start();
        }
        Thread.sleep(500);
        assertThat(resultSize, is(expectedSize));
        assertThat(db.findById("5").getName(), is("UpdateUser5"));
        assertThat(db.findById("14").getName(), is("UpdateUser14"));
        assertNull(db.findById("30"));
        assertNull(db.findById("49"));
        assertThat(db.findAll().size(), is(274));
    }
}