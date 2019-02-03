package ru.job4j.tracker;

import ru.job4j.item.Item;

import java.util.List;
import java.util.function.Predicate;

/**
 * Interface by Tracker.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $1.0$.
 * @since 30.09.2018.
 */
public interface Tracker {

    /**
     * Add items in storage.
     *
     * @param item new item.
     * @return new Item add storage.
     */
    Item add(Item item);

    /**
     * Replace item.
     * @param id functional for find item by id.
     * @param item the item.
     */
    boolean replace(Predicate<String> id, Item item);

    /**
     * Delete item by Id.
     * @param id functional for find item by id.
     */
    boolean delete(Predicate<String> id);

    /**
     * Get list all items.
     * @return list all items.
     */
    List<Item> findAll();

    /**
     * Find item by name.
     * @param key functional for find item by id.
     * @return list of items.
     */
    List<Item> findByName(Predicate<String> key);

    /**
     * Get item by id.
     * @param id functional for find item by id.
     * @return item.
     */
    Item findById(Predicate<String> id);

    /**
     * Add comment for item.
     * @param id functional for find item by id.
     * @return result add comment.
     */
    boolean addComment(Predicate<String> id, String comment);
}
