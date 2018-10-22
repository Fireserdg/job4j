package ru.job4j.vacancy;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Item class test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */
public class ItemTest {

    @Test
    public void whenCreateNewItemThenGetValuesItem() {
        LocalDateTime local = LocalDateTime.parse("2018-08-08T22:19");
        Item item = new Item("Vacancy", "simpleURL", Timestamp.valueOf(local));
        item.setId(1);
        assertThat(item.getVacancy(), is("Vacancy"));
        assertThat(item.getCreate().toLocalDateTime().toString(), is("2018-08-08T22:19"));
        assertThat(item.getId(), is(1));
        assertThat(item.getUrl(), is("simpleURL"));
    }
}