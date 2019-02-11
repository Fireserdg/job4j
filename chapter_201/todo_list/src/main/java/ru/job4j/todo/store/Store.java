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
public interface Store {

    boolean addItem(Item item);

    Item findItemById(int id);

    void deleteItem(int id);

    void updateItem(Item item);

    List<Item> getAllItems();
}
