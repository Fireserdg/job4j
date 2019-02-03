package ru.job4j.input;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестовый класс Validate проверяющий поведение пользователя.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.05.2018.
 */
public class ValidateInputTest {
    /**
     * Поле содержит дефолтный вывод на консоль.
     */
    private final PrintStream stdout = System.out;
    /**
     * Поле содержит буфер результата.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Метод заменяющий вывод на консоль на вывод в память.
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Метод возвращающий вывод на консоль.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    /**
     * Метод проверяющий некорректный ввод пользователя.
     */
    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"invalid", "War", "1"}));
        input.ask("Select", new ArrayList<>());
        assertThat(this.out.toString(), is(String.format("Please enter validate data again."
                                                    + System.lineSeparator()
                                                    + "Please enter validate data again."
                                                    + System.lineSeparator())));
    }
}