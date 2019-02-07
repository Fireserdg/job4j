package ru.job4j.vacancy.parse;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.vacancy.config.Config;
import ru.job4j.vacancy.model.Item;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ParseDocument class test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */
public class ParseDocumentTest {

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

    @Test
    public void whenGetListOfVacanciesOfFirstYear() {
        LocalDateTime minDate = LocalDateTime.parse("2019-01-01T00:00");
        ParseDocument parse = new ParseDocument(config, minDate);
        List<Item> items = parse.getItems();
        assertThat(items.size() > 0, is(true));
        String firstJava = "Ищем java-разработчиков 1 2 3 все [new]";
        String firstDate = "2019-01-01T12:16";
        String secondJava = "Ищем Java-разработчика [new]";
        String secondDate = "2019-01-04T10:04";
        assertThat(items.get(0).getVacancy(), is(firstJava));
        assertThat(items.get(0).getCreate().toLocalDateTime().toString(),
                is(firstDate));
        assertThat(items.get(1).getVacancy(), is(secondJava));
        assertThat(items.get(1).getCreate().toLocalDateTime().toString(),
                is(secondDate));
    }
}