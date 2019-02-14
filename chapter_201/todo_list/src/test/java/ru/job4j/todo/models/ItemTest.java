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
        var item = new Item();
        item.setId(1);
        item.setDesc("123");
        var value = new Timestamp(System.currentTimeMillis());
        item.setCreated(value);
        item.setDone(false);
        assertThat(item.getId(), is(1L));
        assertThat(item.getDesc(), is("123"));
        assertThat(item.getCreated(), is(value));
        assertThat(item.isDone(), is(false));
    }

    @Test
    public void whenGetToStringItem() {
        var item = new Item();
        item.setId(1);
        item.setDesc("123");
        var value = new Timestamp(System.currentTimeMillis());
        item.setCreated(value);
        item.setDone(true);
        var exp = String.format(
                "Item{id=%d, desc=%s, created=%s, done=%s}", 1, "123", value, true);
        assertThat(item.toString(), is(exp));
    }

    @Test
    public void whenComparingTwoObjectsHall() {
        var item = new Item();
        item.setId(1);
        item.setDesc("test_item");
        var theSame = new Item();
        theSame.setId(1);
        theSame.setDesc("test_item");
        var other =  new Item();
        other.setId(2);
        other.setDesc("test_item");
        assertThat(item.equals(theSame), is(true));
        assertThat(item.equals(other), is(false));
        assertThat(item.equals(null), is(false));
        assertThat(item.hashCode() == theSame.hashCode(), is(true));
        assertThat(item.hashCode() == other.hashCode(), is(false));
    }
}