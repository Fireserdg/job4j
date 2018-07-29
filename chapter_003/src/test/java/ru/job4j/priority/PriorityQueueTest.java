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
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenMiddlePriority() {
        queue.put(new Task("low", 7));
        queue.put(new Task("middle", 2));
        queue.put(new Task("low", 4));
        Task result = queue.take();
        assertThat(result.getDesc(), is("middle"));
    }

    @Test
    public void whenLowPriority() {
        queue.put(new Task("low", 1));
        queue.put(new Task("middle low", 4));
        queue.put(new Task("very low", 2));
        Task result = queue.take();
        assertThat(result.getDesc(), is("low"));
    }
}