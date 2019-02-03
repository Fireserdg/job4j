package ru.job4j.input;

import ru.job4j.exception.MenuOutException;

import java.util.List;
import java.util.Scanner;

/**
 * Класс для реализации ввода данных в Tracker.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.05.2018.
 */
public class ConsoleInput implements Input {

    /**
     * Объект для ввода информации от пользователя.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод для получения информации от пользователя.
     *
     * @param question Вопрос адресованный пользователю.
     * @return Строка для ввода информации от пользователя.
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Метод для проверки введенного пользователем пункта меню.
     *
     * @param question Вопрос адрессованный к пользователю.
     * @param range Диапазон значений меню.
     * @return Пункт меню.
     */
    @Override
    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (Integer value: range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Out of menu range");
        }
        return key;
    }
}
