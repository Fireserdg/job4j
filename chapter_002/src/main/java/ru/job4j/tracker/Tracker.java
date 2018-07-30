package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Реализация класса Tracker.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.05.2018.
 */
public class Tracker {

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
     * @param item
     * @return возвращает новую добавленую заявку.
     */
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
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int index = 0; index != this.items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
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
    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
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
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод получения списка по имени.
     * @param key имя для поиска в заявке.
     * @return массив заявок.
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item item: this.items) {
            if (item.getName().equals(key)) {
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
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
