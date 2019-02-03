package ru.job4j.action;

import ru.job4j.input.Input;
import ru.job4j.tracker.Tracker;

/**
 * Реализация интерфейса UserAction.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 12.07.2018.
 */

public interface UserAction {

    /**
     * Метод возвращает ключ опции.
     * @return ключ
     */
    int key();

    /**
     * Основной метод.
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    void execute(Input input, Tracker tracker);

    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return Строка меню
     */
    String info();
}
