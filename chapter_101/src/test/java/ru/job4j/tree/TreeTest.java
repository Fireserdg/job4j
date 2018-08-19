package ru.job4j.tree;

import org.junit.*;

import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Tree.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 18.08.2018.
 */
public class TreeTest {

    /**
     * Contains tree.
     *
     */
    private Tree<Integer> tree;

    /**
     * Create tree for test methods.
     *
     */
    @Before
    public void createTree() {
        this.tree = new Tree<>();
        this.tree.add(1, 2);
        this.tree.add(1, 3);
        this.tree.add(1, 4);
        this.tree.add(4, 5);
        this.tree.add(5, 6);
    }

    @Test
    public void when6ElFindLastThen6() {
        assertThat(this.tree.findBy(6).isPresent(), is(true));
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        assertThat(tree.findBy(7).isPresent(), is(false));
    }

    @Test
    public void when7ElementsAndIterateThroughElement() {
        Iterator<Integer> iterator = this.tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(6));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorGetLastValueAndNextValueShouldException() {
        Iterator<Integer> iterator = this.tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(6));
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenGetIteratorThenInsertElementAndShouldException() {
        Tree<String> tree = new Tree<>();
        tree.add("One", "Two");
        tree.add("One", "Three");
        tree.add("One", "Four");
        tree.add("Four", "Five");
        Iterator<String> iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("One"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Two"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Three"));
        tree.add("One", "Two");
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void whenTreeIsBinary() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 5);
        tree.add(2, 8);
        tree.add(3, 4);
        tree.add(3, 9);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenTreeIsNotBinary() {
        assertThat(this.tree.isBinary(), is(false));
    }
}