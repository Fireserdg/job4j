package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

/**
 * Реализация класса Tracker.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.05.2018.
 */
public class TrackerList implements Tracker {

    /**
     * Массив для хранения заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Определение случайного числа.
     */
    private static final Random RN = new Random();

    /**
     * Метод добавления заявок.
     *
     * @param item item
     * @return возвращает новую добавленую заявку.
     */
    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод для получения уникального идентификатора заявки.
     * @return Возвращает уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод редактирования заявок.
     * @param id Идентификатор заявки.
     * @param item Заявка.
     */
    @Override
    public boolean replace(Predicate<String> id, Item item) {
        boolean result = false;
        for (int index = 0; index != this.items.size(); index++) {
            if (id.test(this.items.get(index).getId())) {
                item.setId(this.items.get(index).getId());
                this.items.set(index, item);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод удаления заявок.
     * @param id Идентификатор заявки.
     */
    @Override
    public boolean delete(Predicate<String> id) {
        boolean result = false;
        for (int index = 0; index < this.items.size(); index++) {
            if (id.test(this.items.get(index).getId())) {
                this.items.remove(index);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод получения списка всех заявок.
     * @return Возвращает массив заявок.
     */
    @Override
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод получения списка по имени.
     * @param key имя для поиска в заявке.
     * @return массив заявок.
     */
    @Override
    public List<Item> findByName(Predicate<String> key) {
        List<Item> result = new ArrayList<>();
        for (Item item: this.items) {
            if (key.test(item.getName())) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Метод получения заявок по Id.
     * @param id уникальное Id заявки.
     * @return Заявка по Id.
     */
    @Override
    public Item findById(Predicate<String> id) {
        Item result = null;
        for (Item item : items) {
            if (id.test(item.getId())) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Метод для добавления комментариев к заявке.
     * @param id функция для поиска id.
     * @return Результат добавления комментария к заявке.
     */
    @Override
    public boolean addComment(Predicate<String> id, String comment) {
        boolean result = false;
        for (Item item: this.items) {
            if (id.test(item.getId())) {
                item.setComments(comment);
                result = true;
                break;
            }
        }
        return result;
    }
}