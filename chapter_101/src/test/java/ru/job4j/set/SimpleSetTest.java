package ru.job4j.set;

import org.junit.*;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Simple Stack.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 13.08.2018.
 */
public class SimpleSetTest {

    /**
     * Contains set.
     *
     */
    private SimpleSet<Integer> set;

    /**
     * Create set for test methods.
     *
     */
    @Before
    public void createSet() {
        set = new SimpleSet<>(4);
    }

    @Test
    public void whenAddItemInSetAndTheSameItemNotAdd() {
        set.add(10);
        set.add(20);
        set.add(5);
        set.add(15);
        assertThat(set.size(), is(4));
        set.add(5);
        set.add(15);
        set.add(10);
        assertThat(set.size(), is(4));
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(10));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(20));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(15));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorGetLastValueAndNextValueShouldException() {
        SimpleSet<String> set = new SimpleSet<>(3);
        set.add("Dog");
        set.add("Dog");
        set.add("Cat");
        set.add("Bird");
        set.add("Cat");
        Iterator<String> iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Dog"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Cat"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Bird"));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }
}