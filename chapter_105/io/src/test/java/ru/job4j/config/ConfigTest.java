package ru.job4j.config;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Config test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-04-06
 */
public class ConfigTest {

    @Test
    public void whenLoadPropertiesFileThenGetValues() {
        var config = new Config("app.properties");
        config.load();
        var valueFirst = config.value("hibernate.dialect");
        var valueLast = config.value("hibernate.connection.password");
        assertThat(valueFirst, is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(valueLast, is("password"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetValueThenException() {
        var config = new Config("app.properties");
        config.load();
        config.value("todo");
    }

    @Test(expected = IllegalStateException.class)
    public void whenLoadFileThenGetException() {
        var config = new Config("app.xxx");
        config.load();
    }
}