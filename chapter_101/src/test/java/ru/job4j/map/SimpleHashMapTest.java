package ru.job4j.map;

import org.junit.Test;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Simple HashMap.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 14.08.2018.
 */
public class SimpleHashMapTest {

    @Test
    public void whenInsertKeyAndValueInMapThenGetValueByKey() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        assertThat(map.insert("Victor", 1), is(true));
        assertThat(map.insert("Artem", 2), is(true));
        assertThat(map.insert("Fedor", 3), is(true));
        assertThat(map.insert("Tom", 4), is(true));
        assertThat(map.insert("Tim", 5), is(false));
        assertThat(map.insert("MMMM", 6), is(true));
        assertThat(map.insert("XXXX", 7), is(false));
        assertThat(map.insert("Max", 8), is(true));
        assertThat(map.insert("Martines", 8), is(true));
        assertThat(map.get("Max"), is(8));
        assertThat(map.get("Tom"), is(4));
        assertThat(map.get("Victor"), is(1));
    }

    @Test
    public void whenInsertKeyAndValueInMapAndDeleteElement() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(8);
        map.insert(1, "One");
        map.insert(2, "Two");
        map.insert(3, "Three");
        map.insert(4, "Four");
        map.insert(5, "Five");
        map.insert(6, "Six");
        map.insert(7, "Seven");
        map.insert(8, "Eight");
        assertThat(map.getSize(), is(8));
        map.insert(9, "Nine");
        map.insert(10, "Ten");
        assertThat(map.getSize(), is(16));
        assertThat(map.delete(6), is(true));
        assertThat(map.delete(4), is(true));
        assertThat(map.delete(11), is(false));
    }

    @Test
    public void whenInsertFiveElementThenGetIterator() {
        SimpleHashMap<Integer, Double> map = new SimpleHashMap<>();
        assertThat(map.insert(1, 2.0), is(true));
        assertThat(map.insert(2, 2.0), is(true));
        assertThat(map.insert(3, 2.0), is(true));
        assertThat(map.insert(4, 2.0), is(true));
        assertThat(map.insert(5, 2.0), is(true));
        Iterator<SimpleHashMap.Entry<Integer, Double>> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getKey(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getKey(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getKey(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getKey(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getKey(), is(5));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenInsertThreeElementThenGetIteratorAndShouldException() {
        SimpleHashMap<Integer, Character> map = new SimpleHashMap<>();
        assertThat(map.insert(1, 'A'), is(true));
        assertThat(map.insert(6, 'B'), is(true));
        assertThat(map.insert(12, 'C'), is(true));
        Iterator<SimpleHashMap.Entry<Integer, Character>> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is('A'));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is('B'));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is('C'));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenGetIteratorThenInsertElementAndShouldException() {
        SimpleHashMap<String, Character> map = new SimpleHashMap<>();
        assertThat(map.insert("Vova", 'A'), is(true));
        assertThat(map.insert("Petr", 'B'), is(true));
        assertThat(map.insert("Mihail", 'C'), is(true));
        Iterator<SimpleHashMap.Entry<String, Character>> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is('B'));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is('A'));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is('C'));
        assertThat(map.insert("Alex", 'D'), is(true));
        iterator.next();
    }
}