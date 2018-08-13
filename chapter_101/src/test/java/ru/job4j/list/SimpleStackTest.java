package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    /**
     * Contains stack.
     *
     */
    private SimpleStack<Integer> stack;

    /**
     * Create stack for test methods.
     *
     */
    @Before
    public void createList() {
        stack = new SimpleStack<>();
    }

    @Test
    public void whenPushValueInStackAndThanPollAndGetValue() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertThat(stack.getSize(), is(4));
        assertThat(stack.poll(), is(4));
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
        assertThat(stack.getSize(), is(0));
        stack.push(10);
        assertThat(stack.getSize(), is(1));
        assertThat(stack.poll(), is(10));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPollNextValueAndGetException() {
        stack.push(1);
        stack.push(2);
        stack.poll();
        stack.poll();
        stack.poll();
    }
}