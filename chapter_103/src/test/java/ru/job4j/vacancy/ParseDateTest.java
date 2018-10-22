package ru.job4j.vacancy;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ParseDate class test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */
public class ParseDateTest {

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
    public void whenConvertTodayDate() {
        String date = "сегодня, 14:49";
        ParseDate parseDate = new ParseDate(date, config);
        LocalDateTime result = parseDate.convertValue();
        date = date.replace("сегодня",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        LocalDateTime expected = LocalDateTime.parse(date,
                DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
        assertThat(result, is(expected));
    }

    @Test
    public void whenConvertYesterdayDate() {
        String date = "вчера, 22:20";
        ParseDate parseDate = new ParseDate(date, config);
        LocalDateTime result = parseDate.convertValue();
        date = date.replace("вчера",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        LocalDateTime expected = LocalDateTime.parse(date,
                DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm")).minusDays(1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenConvertLastDate() {
        String date = "19 окт 18, 15:33";
        ParseDate parseDate = new ParseDate(date, config);
        LocalDateTime result = parseDate.convertValue();
        String expected = "2018-10-19T15:33";
        assertThat(result.toString(), is(expected));
    }
}