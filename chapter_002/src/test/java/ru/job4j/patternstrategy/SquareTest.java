package ru.job4j.patternstrategy;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Тестовый класс прорисовки квадрата.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 11.07.2018.
 */

public class SquareTest {
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(square.draw(), is(new StringBuilder()
                                    .append("++++")
                                    .append("+  +")
                                    .append("+  +")
                                    .append("++++")
                                    .toString()));
    }
}