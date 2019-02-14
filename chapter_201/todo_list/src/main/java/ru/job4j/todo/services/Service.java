package ru.job4j.todo.services;

import ru.job4j.todo.models.Item;

import java.util.List;

/**
 * Service
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-14
 */
public interface Service {

    /**
     * Add item.
     *
     * @param desc description item
     * @return added item
     */
    Item addItem(String desc);

    /**
     * Get all item.
     *
     * @return list of items
     */
    List<Item> getAllItems();
}
