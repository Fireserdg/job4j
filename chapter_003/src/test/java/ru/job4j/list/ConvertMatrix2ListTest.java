package ru.job4j.list;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Convert Matrix to List test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.07.2018.
 */
public class ConvertMatrix2ListTest {

    /**
     * Field contains object of ConvertMatrix2List for Test method.
     */
    private ConvertMatrix2List list = new ConvertMatrix2List();

    @Test
    public void when2to2ArrayThenList4() {
        int[][] input = {{1, 2}, {3, 4}};
        List<Integer> expect = List.of(1, 2, 3, 4);
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }

    @Test
    public void when3to3ArraysThenList9() {
        int[][] input = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> result = list.toList(input);
        assertThat(result, is(expected));
    }

    @Test
    public void when2to4ArraysThenList8() {
        int[][] input = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> result = list.toList(input);
        assertThat(result, is(expected));
    }
}