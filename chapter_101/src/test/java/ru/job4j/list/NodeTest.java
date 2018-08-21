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

    @Test
    public void whenNodeHasCycleAtTheEnd() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = four;
        four.next = first;
        assertThat(four.value, is(4));
        assertThat(Node.hasCycle(first), is(true));
    }

    @Test
    public void whenNodeHasCycleInTheMiddle() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = second;
        four.next = null;
        assertThat(Node.hasCycle(third), is(true));
    }

    @Test
    public void whenNodeHasNotCycle() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = four;
        four.next = null;
        assertThat(Node.hasCycle(first), is(false));
    }
}