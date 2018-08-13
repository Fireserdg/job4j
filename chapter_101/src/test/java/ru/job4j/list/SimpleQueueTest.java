package ru.job4j.list;

import org.junit.*;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    /**
     * Contains queue.
     *
     */
    private SimpleQueue<String> queue;

    /**
     * Create queue for test methods.
     *
     */
    @Before
    public void createList() {
        queue = new SimpleQueue<>();
    }

    @Test
    public void whenAddValueThanPollAndGetValue() {
        queue.push("One");
        queue.push("Two");
        queue.push("Three");
        queue.push("Four");
        assertThat(queue.getSize(), is(4));
        assertThat(queue.poll(), is("One"));
        assertThat(queue.poll(), is("Two"));
        assertThat(queue.poll(), is("Three"));
        assertThat(queue.poll(), is("Four"));
        assertThat(queue.getSize(), is(0));
        queue.push("Five");
        assertThat(queue.getSize(), is(1));
        assertThat(queue.poll(), is("Five"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPollNextValueAndGetException() {
        queue.push("One");
        queue.push("Two");
        queue.poll();
        queue.poll();
        queue.poll();
    }
}