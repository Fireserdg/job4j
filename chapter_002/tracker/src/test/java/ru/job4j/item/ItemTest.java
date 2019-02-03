package ru.job4j.item;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Item test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 03.02.19
 */
public class ItemTest {

    @Test
    public void whenCreateNewItemThenCheckParam() {
        long create = System.currentTimeMillis();
        String itemId = "1";
        String comment = "comment";
        Item item = new Item("Item", "Description", create);
        item.setId(itemId);
        item.setComments(comment);
        assertThat(item.getComments(), is(Collections.singletonList(comment)));
        assertThat(item.getCreate(), is(create));
        assertThat(item.getId(), is(itemId));
        assertThat(item.getDescription(), is("Description"));
        assertThat(item.getName(), is("Item"));
        StringBuilder expected = new StringBuilder();
        expected.append("ID item: 1 Name item: Item Description item: Description")
                .append(System.lineSeparator())
                .append("Комментарий №1: comment");
        assertThat(item.toString(), is(expected.toString()));
    }
}