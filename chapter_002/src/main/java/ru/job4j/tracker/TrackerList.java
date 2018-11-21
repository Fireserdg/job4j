package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        Optional<Item> first = searchItem(id);
        boolean result = first.isPresent();
        if (result) {
            item.setId(first.get().getId());
            this.items.set(this.items.indexOf(first.get()), item);
        }
        return result;
    }

    /**
     * Метод удаления заявок.
     * @param id Идентификатор заявки.
     */
    @Override
    public boolean delete(Predicate<String> id) {
        Optional<Item> item = searchItem(id);
        boolean result = item.isPresent();
        if (result) {
            this.items.remove(item.get());
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
        return this.items.stream().filter(item -> key.test(item.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Метод получения заявок по Id.
     * @param id уникальное Id заявки.
     * @return Заявка по Id.
     */
    @Override
    public Item findById(Predicate<String> id) {
        Optional<Item> first = searchItem(id);
        if (first.isPresent()) {
            return first.get();
        }
        throw new IllegalArgumentException("The user with this id does not exist");
    }

    /**
     * Метод для добавления комментариев к заявке.
     * @param id функция для поиска id.
     * @return Результат добавления комментария к заявке.
     */
    @Override
    public boolean addComment(Predicate<String> id, String comment) {
        Optional<Item> first = searchItem(id);
        boolean result = first.isPresent();
        if (result) {
            first.get().setComments(comment);
        }
        return result;
    }

    /**
     * Поиск Item в коллекции.
     *
     * @param param функция для поиска.
     * @return результат поиска.
     */
    private Optional<Item> searchItem(Predicate<String> param) {
        return this.items.stream().filter(item -> param.test(item.getId()))
                .findFirst();
    }
}