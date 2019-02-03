package ru.job4j.input;

import java.util.List;

public interface Input {

    /**
     * Метод реализующий обмен информации с пользователем.
     *
     * @param question Вопрос.
     * @return Срока для ввода информации от пользователя.
     */
    String ask(String question);

    /**
     *
     * @param question Вопрос адресованный к пользователю.
     * @param range Список пунктов меню.
     * @return Пункт меню.
     */
    int ask(String question, List<Integer> range);
}
