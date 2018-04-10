package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 0.1
 */
public class BubbleSortTest {
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
    BubbleSort bubble = new BubbleSort();
    int[] input = new int[] {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
    int[] result = bubble.sort(input);
    int[] expect = new int[] {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
    assertThat(result, is(expect));
    }
    @Test
    public void whenSortArrayWithNineElementsThenSortedArray() {
        BubbleSort bubble = new BubbleSort();
        int[] input = new int[] {2, 3, 5, 0, 1, 4, 6, 8, 7};
        int[] result = bubble.sort(input);
        int[] expect = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
        assertThat(result, is(expect));
    }
    @Test
    public void whenSortArrayWithSevenElementsThenSortedArray() {
        BubbleSort bubble = new BubbleSort();
        int[] input = new int[] {234, 63, 98, 127, 95, 43, 6};
        int[] result = bubble.sort(input);
        int[] expect = new int[] {6, 43, 63, 95, 98, 127, 234};
        assertThat(result, is(expect));
    }
}
