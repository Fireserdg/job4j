package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 06.05.2018
 */
public class ArrayTwoToThreeTest {
    @Test
    public void whenSortArrayThreeWithTwoArrays() {
        ArrayTwoToThree arrays = new ArrayTwoToThree();
        int[] oneArray = new int[] {1, 2, 3, 4};
        int[] twoArray = new int[] {5, 6, 7, 8};
        int[] result = arrays.arraysThree(oneArray, twoArray);
        int[] expect = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        assertThat(result, is(expect));
    }
    @Test
    public void whenSortArrayThreeWithTwoArraysTenElements() {
        ArrayTwoToThree arrays = new ArrayTwoToThree();
        int[] oneArray = new int[] {22, 24, 26, 28, 30};
        int[] twoArray = new int[] {2, 4, 6, 8, 10};
        int[] result = arrays.arraysThree(oneArray, twoArray);
        int[] expect = new int[] {2, 4, 6, 8, 10, 22, 24, 26, 28, 30};
        assertThat(result, is(expect));
    }
}
