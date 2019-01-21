package ru.job4j.vacancy;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
        String firstJava = "Ищем Java-разработчика [new]";
        String firstDate = "2019-01-03T10:04";
        String secondJava = "Вакансия: Ведущий Java-разработчик [new]";
        String secondDate = "2019-01-08T11:31";
        assertThat(items.get(0).getVacancy(), is(firstJava));
        assertThat(items.get(0).getCreate().toLocalDateTime().toString(),
                is(firstDate));
        assertThat(items.get(1).getVacancy(), is(secondJava));
        assertThat(items.get(1).getCreate().toLocalDateTime().toString(),
                is(secondDate));
    }
}