package ru.job4j.vacancy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Config class test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */
public class ConfigTest {

    @Test
    public void whenLoadLoadConfigFileThenGetValue() {
        String property = "app.properties";
        Config config = new Config();
        config.loadConfig(property);
        String expected = "postgres";
        String result = config.getValue("jdbc.username");
        assertThat(result, is(expected));
    }
}