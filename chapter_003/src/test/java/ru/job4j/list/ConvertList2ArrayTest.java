package ru.job4j.list;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test convert list for array.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 27.07.2018.
 */
public class ConvertList2ArrayTest {

    /**
     * Field contains object of ConvertList2Array for Test method.
     */
    private ConvertList2Array list = new ConvertList2Array();

    @Test
    public void when7ElementThen9() {
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6, 7), 3);
        int[][] expect = {{1, 2, 3},
                          {4, 5, 6},
                          {7, 0, 0}};
        assertThat(result, is(expect));
    }

    @Test
    public void when5ElementThen6() {
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5), 2);
        int[][] expect = {{1, 2, 3},
                          {4, 5, 0}};
        assertThat(result, is(expect));
    }

    @Test
    public void when9ElementThen12() {
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 4);
        int[][] expect = {{1, 2, 3},
                          {4, 5, 6},
                          {7, 8, 9},
                          {0, 0, 0}};
        assertThat(result, is(expect));
    }

    @Test
    public void when6ElementThen6() {
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6), 2);
        int[][] expect = {{1, 2, 3},
                          {4, 5, 6}};
        assertThat(result, is(expect));
    }

    @Test
    public void when2ArraysInListThenOneList() {
        List<Integer> result = list.convert(List.of(
                new int[]{1, 2, 3}, new int[]{4, 5, 6}));
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        assertThat(result, is(expected));
    }

    @Test
    public void when3ArrasInListThenOneList() {
        List<Integer> result = list.convert(
                List.of(new int[]{1, 2, 3, 4}, new int[]{5, 6}, new int[]{7, 8, 9}));
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(result, is(expected));
    }
}