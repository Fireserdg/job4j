package ru.job4j.tracker;

import java.util.Arrays;
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
    private final Item[] items = new Item[100];

    /**
     * Определение случайного числа.
     */
    private static final Random RN = new Random();

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод добавления заявок.
     *
     * @param item
     * @return возвращает новую добавленую заявку.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
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
    public void replace(String id, Item item) {
        for (int index = 0; index != position; index++) {
            if (this.items[index].getId().equals(id)) {
                item.setId(this.items[index].getId());
                this.items[index] = item;

            }
        }
    }

    /**
     * Метод удаления заявок.
     * @param id Идентификатор заявки.
     */
    public void delete(String id) {
        for (int index = 0; index != position; index++) {
            if (this.items[index].getId().equals(id)) {
                for (int j = index; j != position; j++) {
                    items[j] = items[j + 1];
                }
                position--;
                break;
            }
        }
    }

    /**
     * Метод получения списка всех заявок.
     * @return Возвращает массив заявок.
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * Метод получения списка по имени.
     * @param key имя для поиска в заявке.
     * @return массив заявок.
     */
    public Item[] findByName(String key) {
        int count = 0;
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            if (items[index].getName().equals(key)) {
                result[count] = this.items[index];
                count++;
            }
        }
        return Arrays.copyOf(result, count);
    }

    /**
     * Метод получения заявок по Id.
     * @param id уникальное Id заявки.
     * @return Заявка по Id.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
