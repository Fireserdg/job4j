package ru.job4j.todo.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.todo.models.Item;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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

    private static DbStore instance = DbStore.INSTANCE;

    private Timestamp created = new Timestamp(System.currentTimeMillis());

    @Before
    public void before() {
        Item item = new Item();
        item.setDesc("123");
        item.setCreated(created);
        item.setDone(false);
        instance.addItem(item);
    }

    @After
    public void after() {
        List<Item> items = instance.getAllItems();
        Item item = items.get(items.size() - 1);
        instance.deleteItem(item.getId());
    }

    @Test
    public void whenAddItemToDataBase() {
        Item item = new Item();
        item.setDesc("testItem");
        item.setCreated(created);
        item.setDone(false);
        instance.addItem(item);
        int id = instance.addItem(item);
        List<Item> items = instance.getAllItems();
        assertThat(items.get(items.size() - 1).getId(), is(id));
        assertThat(items.get(items.size() - 1).getDesc(), is("testItem"));
    }

    @Test
    public void whenGetAllItem() {
        List<Item> items = instance.getAllItems();
        assertThat(items.get(items.size() - 1).getDesc(), is("123"));
        assertThat(items.get(items.size() - 1).getCreated(), is(created));
    }

    @Test
    public void whenUpdateItem() {
        List<Item> items = instance.getAllItems();
        Item item = items.get(items.size() - 1);
        item.setDone(true);
        instance.updateItem(item);
        assertThat(instance.findItemById(item.getId()).get().isDone(), is(true));
    }

    @Test
    public void whenFindItemById() {
        Item item = new Item();
        item.setDesc("test");
        item.setCreated(created);
        item.setDone(false);
        int id = instance.addItem(item);
        Optional<Item> expected = instance.findItemById(id);
        Item finds = expected.isEmpty() ? new Item() : expected.get();
        assertThat(finds.getDesc(), is("test"));
        assertThat(finds.getId(), is(id));
        instance.deleteItem(id);
    }
}