package ru.job4j.list;

import org.junit.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Test class SingleLinkedList.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 10.08.2018.
 */

public class SingleLinkedListTest {

    /**
     * Contains list dates.
     *
     */
    private SingleLinkedList<Integer> list;

    /**
     * Create new list and add date.
     *
     */
    @Before
    public void beforeTest() {
        list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(2), is(1));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenAddThreeElementsThenDeleteFirstElement() {
        assertThat(list.delete(), is(3));
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(1));
        assertThat(list.getSize(), is(2));
    }
}