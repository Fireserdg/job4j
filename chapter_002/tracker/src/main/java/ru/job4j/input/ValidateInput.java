package ru.job4j.input;

import ru.job4j.exception.MenuOutException;

import java.util.List;

/**
 * Класс для обработки исключительных ситуаций в Tracker.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 14.07.2018.
 */
public class ValidateInput implements Input {

    private Input input;

    /**
     *
     * @param input входящий объект, реализущий интерфейс Input.
     */
    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Метод для получения информации от пользователя.
     *
     * @param question Вопрос к пользователю.
     * @return Сроковое значение ответа.
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Метод реализующий проверку корректного ввода от пользователя.
     *
     * @param question Передаваемый вопрос.
     * @param range Диапозон пунктов меню.
     * @return Пункт меню.
     */
    @Override
    public int ask(String question, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
