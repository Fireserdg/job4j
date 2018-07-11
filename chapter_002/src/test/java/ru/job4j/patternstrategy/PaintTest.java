package ru.job4j.patternstrategy;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Тестовый класс по отображению фигур.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 11.07.2018.
 */
public class PaintTest {
    @Test
    public void whenDrawSquare() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                                                    .append("++++")
                                                    .append("+  +")
                                                    .append("+  +")
                                                    .append("++++")
                                                    .append(System.lineSeparator())
                                                    .toString()));
        System.setOut(stdout);
    }
    @Test
    public void whenDrawTriangle() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                                                    .append("  +  ")
                                                    .append(" + + ")
                                                    .append("+++++")
                                                    .append(System.lineSeparator())
                                                    .toString()));
        System.setOut(stdout);
    }

}