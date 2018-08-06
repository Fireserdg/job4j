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
public class MatrixTestJagged {

    /**
     * Contains Iterator.
     */
    private Iterator<Integer> it;

    /**
     * Declare variables Iterator.
     */
    @Before
    public void setIt() {
        it = new Matrix(new int[][] {{1}, {2, 3, 4, 5}, {6, 7}, {8, 9, 10, 11, 12, 13, 14}});
    }

    @Test
    public void whenGetSumAllValuesInArrays() {
        int result = 0;
        while (it.hasNext()) {
            result += it.next();
        }
        assertThat(result, is(105));
    }

    @Test
    public void whenGetFifthValuesAndHasNextTrue() {
        it.next();
        it.next();
        it.next();
        it.next();
        int count = it.next();
        boolean result = it.hasNext();
        assertThat(count, is(5));
        assertThat(result, is(true));
    }
}