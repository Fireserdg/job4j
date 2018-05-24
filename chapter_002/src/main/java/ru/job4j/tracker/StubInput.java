package ru.job4j.tracker;
/**
 * Реализация класса StubInput.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.05.2018.
 */
public class StubInput implements Input {
    private final String[] answers;
    private int positions;

    public StubInput(String[] answers) {
        this.answers = answers;
    }
    @Override
    public String ask(String question) {
        return answers[positions++];
    }
}
