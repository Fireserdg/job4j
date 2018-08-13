package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LinkedSetTest {

    /**
     * Contains set.
     *
     */
    private LinkedSet<Integer> set;

    /**
     * Create set for test methods.
     *
     */
    @Before
    public void createSet() {
        this.set = new LinkedSet<>();
    }

    @Test
    public void whenAddItemInSetAndTheSameItemNotAdd() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        assertThat(set.size(), is(4));
        set.add(2);
        assertThat(set.size(), is(4));
        set.add(4);
        assertThat(set.size(), is(4));
    }

    @Test
    public void whenAddFourElementAndTwoTheSameThenIteratorShowFourItem() {
        set.add(10);
        set.add(20);
        set.add(35);
        set.add(10);
        set.add(60);
        set.add(35);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(10));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(20));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(35));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(60));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorGetLastValueAndNextValueShouldException() {
        LinkedSet<String> set = new LinkedSet<>();
        set.add("Alex");
        set.add("Fedor");
        set.add("Ben");
        set.add("Alex");
        set.add("Mike");
        set.add("Fedor");
        Iterator<String> iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Alex"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Fedor"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Ben"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Mike"));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }
}