package ru.job4j.crud.store;

import org.junit.After;
import org.junit.Test;
import ru.job4j.crud.Config;
import ru.job4j.crud.models.Role;
import ru.job4j.crud.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test DataBaseStore
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 23.11.18
 */
public class DbStoreTest {

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

    @Test
    public void whenOperationByUsersThenGetResult() {
        DbStore db = DbStore.getInstance();
        User one = new User("1", "test",
                "login", "123",  "email",
                System.currentTimeMillis(), Role.USER);
        User two = new User("2", "newName",
                "newLogin", "123", "NewEmail",
                System.currentTimeMillis(), Role.ADMIN);
        db.add(one);
        db.add(two);
        assertThat(db.findAll().size(), is(2));
        assertThat(db.findById(one.getId()), is(one));
        assertThat(db.findById(two.getId()).getCreate(), is(two.getCreate()));
        List<User> expected = Arrays.asList(one, two);
        List<User> result = db.findAll();
        assertThat(result, is(expected));
        User user = new User(one.getId(), "newName",
                "newLogin", "NewEmail", "333",
                one.getCreate(), Role.ADMIN);
        db.update(user);
        User afterUp = db.findById(user.getId());
        assertThat(afterUp.getName(), is(user.getName()));
        assertThat(afterUp.getLogin(), is(user.getLogin()));
        assertThat(afterUp.getEmail(), is(user.getEmail()));
        assertThat(afterUp.getRole(), is(user.getRole()));
        db.delete(one.getId());
        assertThat(db.findAll().size(), is(1));
    }
}