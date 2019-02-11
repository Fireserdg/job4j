package ru.job4j.todo.store;

import org.junit.Test;
import ru.job4j.todo.models.Item;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-11
 */
public class DbStoreTest {

    @Test
    public void whenAddItemThenUpdateFindByIdThenDelete() {
        DbStore instance = DbStore.INSTANCE;
        Item item = new Item();
        item.setDesc("123");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);
        instance.addItem(item);
        List<Item> allItems = instance.getAllItems();
        assertThat(allItems.size(), is(1));
        assertThat(allItems.get(0).getDesc(), is("123"));
        assertThat(allItems.get(0).isDone(), is(false));
        int id = allItems.get(0).getId();
        Item find = instance.findItemById(id);
        assertThat(find.getDesc(), is("123"));
        item.setDone(true);
        instance.updateItem(item);
        assertThat(instance.getAllItems().get(0).isDone(), is(true));
        instance.deleteItem(id);
        assertThat(instance.getAllItems().size(), is(0));
    }
}