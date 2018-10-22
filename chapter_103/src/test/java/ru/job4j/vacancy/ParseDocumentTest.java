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
        LocalDateTime minDate = LocalDateTime.parse("2018-01-01T00:00");
        ParseDocument parse = new ParseDocument(config, minDate);
        List<Item> items = parse.getItems();
        assertThat(items.size() > 0, is(true));
        String firstJava = "Java/BackEnd/Middle Мск/Офис/FullTime (150) [new]";
        String firstDate = "2018-01-08T14:43";
        String secondJava = "Java Developer, СПб/Мск/Долгопрудный, ЗП до 200k net [new]";
        String secondDate = "2018-01-09T15:41";
        assertThat(items.get(0).getVacancy(), is(firstJava));
        assertThat(items.get(0).getCreate().toLocalDateTime().toString(),
                is(firstDate));
        assertThat(items.get(1).getVacancy(), is(secondJava));
        assertThat(items.get(1).getCreate().toLocalDateTime().toString(),
                is(secondDate));
    }
}