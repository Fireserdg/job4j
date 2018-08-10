package ru.job4j.list;

import org.junit.*;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test class for Simple Array List.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 11.08.2018.
 */
public class SimpleArrayListTest {

    /**
     * Contains list.
     *
     */
    private SimpleArrayList<Integer> list;

    /**
     * Create list for test methods.
     *
     */
    @Before
    public void createList() {
        list = new SimpleArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddLastValueToListWithDefaultSizeAndChangeSize() {
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        assertThat(list.getSize(), is(10));
        list.add(10);
        assertThat(list.getSize(), is(20));
        assertThat(list.get(10), is(10));
        assertThat(list.get(5), is(5));
    }

    @Test
    public void whenGetValueInListByIndex() {
        assertThat(list.get(3), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetValueInListByIndexThenException() {
        list.get(11);
    }

    @Test
    public void whenIteratorGetNextValueAndHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(0));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));

    }
    @Test (expected = ConcurrentModificationException.class)
    public void whenAddValueToListAfterGetIteratorAndShouldException() {
        SimpleArrayList<String> listTwo = new SimpleArrayList<>(4);
        listTwo.add("Cat");
        listTwo.add("Dog");
        listTwo.add("Bird");
        Iterator<String> iterator = listTwo.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        listTwo.add("Monkey");
        iterator.next();
    }
}