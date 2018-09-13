package ru.job4j.function;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * Count function test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $1.0$.
 * @since 13.09.2018.
 */
public class CountFunctionTest {

    /**
     * Contains count.
     *
     */
    private CountFunction count;

    /**
     * Create object for test.
     *
     */
    @Before
    public void createObject() {
        count = new CountFunction();
    }

    @Test
    public void whenDiapasonFrom1To3ThenResultLineFunction() {
        List<Double> result = count.diapason(1, 3,
                (start, end)-> (start * 2) + 1);
        List<Double> expected = Arrays.asList(3D, 5D, 7D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenDiapasonFrom2To4ThenResultQuadraticFunction() {
        List<Double> result = count.diapason(2, 4,
                (start, end)-> (Math.pow(start, 2)));
        List<Double> expected = Arrays.asList(4D, 9D, 16D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenDiapasonFrom1To3ThenResultLogarithmicFunction() {
        List<Double> result = count.diapason(1, 3,
                (start, end)-> (Math.log(start)));
        List<Double> expected = Arrays.asList(0D, 0.69D, 1.09D);
        assertThat(result.get(0), is(expected.get(0)));
        assertThat(result.get(1), closeTo(expected.get(1), 0.01));
        assertThat(result.get(2), closeTo(expected.get(2), 0.01));
    }
}