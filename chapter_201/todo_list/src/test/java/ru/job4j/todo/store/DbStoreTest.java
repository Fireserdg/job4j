package ru.job4j.todo.store;

import org.junit.*;
import ru.job4j.todo.models.Item;

import java.sql.Timestamp;

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

    /**
     * Store
     *
     */
    private static Store<Item> instance = DbStore.INSTANCE;

    /**
     * Created timestamp for test
     *
     */
    private Timestamp created = new Timestamp(System.currentTimeMillis());

    @Before
    public void before() {
        var item = new Item();
        item.setDesc("123");
        item.setCreated(created);
        item.setDone(false);
        instance.addItem(item);
    }

    @After
    public void after() {
        var items = instance.getAllItems();
        Item item = items.get(items.size() - 1);
        instance.deleteItem(item.getId());
    }

    @Test
    public void whenAddItemToDataBase() {
        var item = new Item();
        item.setDesc("testItem");
        item.setCreated(created);
        item.setDone(false);
        var rst = instance.addItem(item);
        var items = instance.getAllItems();
        assertThat(items.get(items.size() - 1).getId(), is(rst.getId()));
        assertThat(items.get(items.size() - 1).getDesc(), is("testItem"));
    }

    @Test
    public void whenGetAllItem() {
        var items = instance.getAllItems();
        assertThat(items.get(items.size() - 1).getDesc(), is("123"));
        assertThat(items.get(items.size() - 1).getCreated(), is(created));
    }

    @Test
    public void whenUpdateItem() {
        var items = instance.getAllItems();
        var item = items.get(items.size() - 1);
        item.setDone(true);
        instance.updateItem(item);
        assertThat(instance.findItemById(item.getId()).isDone(), is(true));
    }

    @Test
    public void whenFindItemByIdThenDelete() {
        var item = new Item();
        item.setDesc("test");
        item.setCreated(created);
        item.setDone(false);
        var rst = instance.addItem(item);
        var id = rst.getId();
        var expected = instance.findItemById(id);
        assertThat(expected.getDesc(), is("test"));
        assertThat(expected.getId(), is(id));
        instance.deleteItem(id);
    }

//    @Test
//    public void whenDelete() {
//        instance.deleteItem(1000);
//    }
}