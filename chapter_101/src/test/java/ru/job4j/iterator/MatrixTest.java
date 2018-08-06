package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test class Iterator for Matrix array.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 06.08.2018.
 */
public class MatrixTest {

    /**
     * Contains Iterator.
     */
    private Iterator<Integer> it;

    /**
     * Declare variables Iterator.
     */
    @Before
    public void setIt() {
        it = new Matrix(new int[][] {{1, 2}, {3, 4}});
    }

    @Test
    public void whenIterateMatrixArray() {
        it.next();
        int result = it.next();
        assertThat(result, is(2));
    }

    @Test
    public void whenGetSumValueMatrixArray() {
        int result = 0;
        while (it.hasNext()) {
            result += it.next();
        }
        assertThat(result, is(10));
    }

    @Test
    public void whenLastCountAndHasNextFalse() {
        it.next();
        it.next();
        it.next();
        it.next();
        boolean result = it.hasNext();
        assertThat(result, is(false));
    }

    @Test
    public void whenFirstCountInSecondArrayAndHasNextTrue() {
        Matrix it = new Matrix(new int[][] {{1, 2}, {3, 4}});
        it.next();
        it.next();
        it.next();
        boolean result = it.hasNext();
        assertThat(result, is(true));
    }
}