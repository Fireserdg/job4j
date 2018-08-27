package ru.job4j.blocking;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Simple blocking queue.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 27.08.2018.
 */
public class SimpleBlockingQueueTest {

    /**
     * Contains first value.
     *
     */
    private int valueFirst;

    /**
     * Contains second value.
     *
     */
    private int valueSecond;

    @Test
    public void when() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(6);
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 8; i++) {
                        queue.offer(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    valueFirst = queue.pool();
                    valueSecond = queue.pool();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(queue.size(), is(6));
        assertThat(valueFirst, is(0));
        assertThat(valueSecond, is(1));
    }
}