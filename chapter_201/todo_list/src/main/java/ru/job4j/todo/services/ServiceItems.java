package ru.job4j.todo.services;

import ru.job4j.todo.models.Item;
import ru.job4j.todo.store.DbStore;
import ru.job4j.todo.store.Store;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service items.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-12
 */
public enum  ServiceItems {

    INSTANCE;

    private static final Store STORE = DbStore.INSTANCE;


    public Item addItem(String desc) {
        Item item = new Item();
        item.setDesc(desc);
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);
        int id = STORE.addItem(item);
        item.setId(id);
        return item;
    }

    public void deleteItem(int id) {
        STORE.deleteItem(id);
    }

    public List<Item> getAllItems() {
        return STORE.getAllItems().stream()
                .sorted(Comparator.comparing(Item::getId))
                .collect(Collectors.toList());
    }

    public void close() {
        STORE.close();
    }
}
