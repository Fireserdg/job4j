package ru.job4j.tracker;

import java.util.List;

/**
 * Реализация класса StubInput.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.05.2018.
 */
public class StubInput implements Input {

    /**
     * Массив из действий пользователя.
     */
    private final String[] answers;

    /**
     * Позиция в индексе массива.
     */
    private int positions = 0;

    /**
     * Метод получения действия пользователя.
     *
     * @param answers Массив действий пользователя.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     *
     * @param question Вопрос.
     * @return Ответ из массива.
     */
    @Override
    public String ask(String question) {
        return answers[positions++];
    }

    /**
     *
     * @param question Вопрос адресованный к пользователю.
     * @param range Список пунктов меню.
     * @return Номер пункта меню.
     */
    @Override
    public int ask(String question, List<Integer> range) {
        return Integer.valueOf(answers[positions++]);
    }
}
