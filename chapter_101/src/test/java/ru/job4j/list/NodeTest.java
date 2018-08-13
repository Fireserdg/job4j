package ru.job4j.list;

import org.junit.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Linked list Node.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 13.08.2018.
 */
public class NodeTest {

    /**
     * Contains first Node.
     *
     */
    private Node<Integer> first;

    /**
     * Contains second Node.
     *
     */
    private Node<Integer> second;

    /**
     * Contains third Node.
     *
     */
    private Node<Integer> third;

    /**
     * Contains four Node.
     *
     */
    private Node<Integer> four;

    /**
     * Create Node linked list.
     *
     */
    @Before
    public void createNode() {
        first = new Node<>(1);
        second = new Node<>(2);
        third = new Node<>(3);
        four = new Node<>(4);
    }

    @Test
    public void whenNodeHasCycleAtTheEnd() {
        first.next = second;
        second.next = third;
        third.next = four;
        four.next = first;
        assertThat(four.value, is(4));
        assertThat(Node.hasCycle(first), is(true));
    }

    @Test
    public void whenNodeHasCycleInTheMiddle() {
        first.next = second;
        second.next = third;
        third.next = second;
        four.next = null;
        assertThat(Node.hasCycle(third), is(true));
    }

    @Test
    public void whenNodeHasNotCycle() {
        first.next = second;
        second.next = third;
        third.next = four;
        four.next = null;
        assertThat(Node.hasCycle(first), is(false));
    }
}