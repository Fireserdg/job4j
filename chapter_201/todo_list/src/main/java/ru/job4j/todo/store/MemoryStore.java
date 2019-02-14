package ru.job4j.todo.store;

import ru.job4j.todo.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Memory store
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-14
 */
public enum MemoryStore implements Store<Item> {

    /**
     * Instance
     *
     */
    INSTANCE;

    /**
     *
     * Storage
     */
    private final List<Item> list = new CopyOnWriteArrayList<>();

    /**
     * Increment
     *
     */
    private final AtomicInteger increment = new AtomicInteger(1);

    /**
     * Add Item to database.
     *
     * @param item item.
     * @return item id;
     */
    @Override
    public Item addItem(Item item) {
        item.setId(increment.getAndDecrement());
        list.add(item);
        return item;
    }

    /**
     * Find item by id
     *
     * @param id item id
     * @return item
     */
    @Override
    public Item findItemById(long id) {
        return list.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Delete item
     *
     * @param id item id
     */
    @Override
    public void deleteItem(long id) {
        list.remove(list.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new));
    }

    /**
     * Update item
     *
     * @param item item
     */
    @Override
    public void updateItem(Item item) {
        list.set(list.indexOf(item), item);
    }

    /**
     * Get all items from database.
     *
     * @return list of items
     */
    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(list);
    }
}
