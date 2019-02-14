package ru.job4j.todo.services;

import ru.job4j.todo.models.Item;
import ru.job4j.todo.store.*;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service items.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-12
 */
public enum  ServiceItems implements Service {

    /**
     * Instance service
     *
     */
    INSTANCE;

    /**
     * Store for contains items.
     *
     */
    private static final Store<Item> STORE = DbStore.INSTANCE;

    /**
     * Add item.
     *
     * @param desc description item
     * @return added item
     */
    @Override
    public Item addItem(String desc) {
        Item item = new Item();
        item.setDesc(desc);
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);
        return  STORE.addItem(item);
    }

    /**
     * Get all item.
     *
     * @return list of items
     */
    @Override
    public List<Item> getAllItems() {
        return STORE.getAllItems().stream()
                .sorted(Comparator.comparing(Item::getId))
                .collect(Collectors.toList());
    }
}
