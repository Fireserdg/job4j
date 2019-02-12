package ru.job4j.todo.models;

import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test for item
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-11
 */
public class ItemTest {

    @Test
    public void whenCreateItemThenResult() {
        Item item = new Item();
        item.setId(1);
        item.setDesc("123");
        Timestamp value = new Timestamp(System.currentTimeMillis());
        item.setCreated(value);
        item.setDone(false);
        assertThat(item.getId(), is(1));
        assertThat(item.getDesc(), is("123"));
        assertThat(item.getCreated(), is(value));
        assertThat(item.isDone(), is(false));
    }

    @Test
    public void whenGetToStringItem() {
        Item item = new Item();
        item.setId(1);
        item.setDesc("123");
        Timestamp value = new Timestamp(System.currentTimeMillis());
        item.setCreated(value);
        item.setDone(true);
        String exp = String.format(
                "Item{id=%d, desc=%s, created=%s, done=%s}", 1, "123", value, true);
        assertThat(item.toString(), is(exp));
    }
}