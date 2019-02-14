package ru.job4j.todo.store;

import org.junit.Test;
import ru.job4j.todo.models.Item;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Memory store test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-14
 */
public class MemoryStoreTest {

    @Test
    public void whenCreateUpdateDeleteItem() {
        Store<Item> store = MemoryStore.INSTANCE;
        Item first = new Item();
        first.setDesc("desc1");
        first.setDone(false);
        first.setCreated(new Timestamp(System.currentTimeMillis()));
        Item second = new Item();
        second.setDesc("desc2");
        second.setDone(false);
        second.setCreated(new Timestamp(System.currentTimeMillis()));
        first = store.addItem(first);
        second = store.addItem(second);
        Item item = store.findItemById(1);
        assertThat(store.getAllItems().size(), is(2));
        assertThat(item.getDesc(), is("desc1"));
        first.setDesc("newDesc");
        store.updateItem(first);
        store.deleteItem(second.getId());
        assertThat(store.findItemById(1).getDesc(), is("newDesc"));
        assertThat(store.getAllItems().size(), is(1));
    }
}