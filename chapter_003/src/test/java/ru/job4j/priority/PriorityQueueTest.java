package ru.job4j.priority;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Priority Queue test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 26.07.2018.
 */
public class PriorityQueueTest {

    /**
     * Field contains object of PriorityQueue for Test method.
     */
    private PriorityQueue queue = new PriorityQueue();

    @Test
    public void whenHigherPriority() {
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        var result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenMiddlePriority() {
        queue.put(new Task("low", 7));
        queue.put(new Task("middle", 2));
        queue.put(new Task("low", 4));
        var result = queue.take();
        assertThat(result.getDesc(), is("middle"));
    }

    @Test
    public void whenLowPriority() {
        queue.put(new Task("high low", 1));
        queue.put(new Task("very low", 4));
        queue.put(new Task("middle low", 2));
        queue.put(new Task("low", 3));
        var result = queue.take();
        assertThat(result.getDesc(), is("high low"));
    }

    @Test
    public void whenFivePrioritySet() {
        queue.put(new Task("very low", 7));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 2));
        queue.put(new Task("middle", 4));
        queue.put(new Task("low", 5));
        var result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
}