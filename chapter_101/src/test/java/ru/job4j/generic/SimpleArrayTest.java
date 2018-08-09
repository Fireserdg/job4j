package ru.job4j.generic;

import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test class for check Simple Array.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 06.08.2018.
 */
public class SimpleArrayTest {

    @Test
    public void whenAddItemInArray() {
        SimpleArray<Integer> array = new SimpleArray<>(10);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        int result = array.get(2);
        assertThat(result, is(3));
    }

    @Test
    public void whenDeleteItemFromTheArray() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.delete(0);
        int result = array.get(0);
        assertThat(result, is(2));
    }
    @Test
    public void whenDeleteLastItemAndAfterThatAddItem() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.delete(3);
        array.add(10);
        int result = array.get(3);
        assertThat(result, is(10));
    }

    @Test
    public void whenGetItemByIndexAndResultTrue() {
        SimpleArray<String> array = new SimpleArray<>(4);
        array.add("1");
        array.add("2");
        array.add("3");
        String result = array.get(2);
        assertThat(result, is("3"));
    }

    @Test
    public void whenSetNewItemInArray() {
        SimpleArray<String> array = new SimpleArray<>(4);
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.set(1, "8");
        assertThat(array.get(1), is("8"));
    }

    @Test
    public void whenAddFourItemInArrayAndWantShowResult() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        assertThat(array.toString(), is("[1, 2, 3, 4]"));
    }

    @Test
    public void whenIterateSimpleArrayEachItem() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        Iterator<Integer> iter = array.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(2));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(4));
        assertThat(iter.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(1);
        array.add(2);
        array.add(3);
        Iterator<Integer> iter = array.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(2));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(3));
        iter.next();
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddElementAndShouldException() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetItemByIndexAndShouldException() {
        SimpleArray<Double> array = new SimpleArray<>(3);
        array.add(1.0);
        array.add(2.0);
        array.add(3.0);
        array.get(3);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenDeleteItemByIndexAndShouldException() {
        SimpleArray<Character> array = new SimpleArray<>(3);
        array.add('A');
        array.add('B');
        array.add('C');
        array.delete(3);
    }
}