package ru.job4j.convert;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Test convert matrix to list.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 03.02.19
 */
public class ConvertMatrixToListTest {

    @Test
    public void whenConvertMatrixFrom3ArrayToOneListOfNumbers() {
        Integer[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> result = new ConvertMatrixToList().convertToList(matrix);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(result, is(expected));
    }

    @Test
    public void whenConvertMatrixFrom2ArrayToOneListOfNumbers() {
        Integer[][] matrix = {{55, 108, 33, 55}, {4, 41, 0, 22}};
        List<Integer> result = new ConvertMatrixToList().convertToList(matrix);
        List<Integer> expected = Arrays.asList(55, 108, 33, 55, 4, 41, 0, 22);
        assertThat(result, is(expected));
    }
}