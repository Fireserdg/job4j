package ru.job4j.list;

import org.junit.*;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test class for Simple Linked List.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 11.08.2018.
 */
public class SimpleLinkedListTest {

    /**
     * Contains list.
     *
     */
    private SimpleLinkedList<Integer> list;

    /**
     * Create list for test methods.
     *
     */
    @Before
    public void createList() {
        list = new SimpleLinkedList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddElementsToListAndChangeSize() {
        list.add(4);
        list.add(5);
        assertThat(list.size(), is(6));
        list.add(6);
        assertThat(list.size(), is(7));
    }

    @Test
    public void whenGetValueInListByIndex() {
        assertThat(list.get(0), is(0));
        assertThat(list.get(2), is(2));
        assertThat(list.get(3), is(3));
    }

    @Test
    public void whenDeleteValueByIndex() {
        assertThat(list.delete(1), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
        assertThat(list.delete(2), is(3));
        assertThat(list.get(1), is(2));
        assertThat(list.delete(1), is(2));
        assertThat(list.get(0), is(0));
        assertThat(list.delete(0), is(0));
        assertThat(list.size(), is(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetValueInListByIndexThenException() {
        list.get(7);
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
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenAddValueToListAfterGetIteratorAndShouldException() {
        SimpleLinkedList<Double> listDouble = new SimpleLinkedList<>();
        listDouble.add(1.0);
        listDouble.add(2.0);
        listDouble.add(3.0);
        Iterator<Double> iterator = listDouble.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        listDouble.add(4.0);
        iterator.next();
    }

    @Test (expected = NoSuchElementException.class)
    public void whenCallIteratorNextAndShouldException() {
        SimpleLinkedList<String> listDouble = new SimpleLinkedList<>();
        listDouble.add("1");
        listDouble.add("2");
        listDouble.add("3");
        Iterator<String> iterator = listDouble.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("1"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("2"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("3"));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }
}