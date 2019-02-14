package ru.job4j.todo.store;

import ru.job4j.todo.models.Item;

import java.util.List;

/**
 * Store for contains items.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-11
 */
public interface Store<T> {

    /**
     * Add Item to database.
     *
     * @param item item.
     * @return item id;
     */
    Item addItem(T item);

    /**
     * Find item by id
     *
     * @param id item id
     * @return item
     */
    Item findItemById(long id);

    /**
     * Delete item
     *
     * @param id item id
     */
    void deleteItem(long id);

    /**
     * Update item
     *
     * @param item item
     */
    void updateItem(T item);

    /**
     * Get all items from database.
     *
     * @return list of items
     */
    List<T> getAllItems();
}
