package ru.job4j.safelist;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Safe Array list.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 26.08.2018.
 */
public class SafeArrayListTest {

    /**
     * Contains list.
     *
     */
    private SafeArrayList<Integer> list;

    /**
     * Contains Thread.
     *
     */
    private Thread first;

    /**
     * Create new Thread and new Safe Array List for test.
     *
     */
    @Before
    public void createThread() {
        list = new SafeArrayList<>(100);
        first = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 97; i++) {
                    list.add(i);
                }
            }
        });
    }

    @Test
    public void whenAdd3ItemSecondThreadThenCheckSizeAndGetItem()
            throws InterruptedException {
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 97; i < 100; i++) {
                    list.add(i);
                }

            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(list.size(), is(100));
        assertThat(list.get(0), is(0));
        assertThat(list.get(1), is(1));
        assertThat(list.get(2), is(2));
        assertThat(list.get(33), is(33));
        assertThat(list.get(8), is(8));
        assertThat(list.get(99), is(99));
    }

    @Test
    public void whenIteratorListAndHasNextFalse() throws InterruptedException {
        first.start();
        Thread.sleep(50);
        Iterator<Integer> iterator = list.iterator();
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 100; i++) {
                    iterator.next();
                }
            }
        });
        second.start();
        first.join();
        second.join();
        assertThat(iterator.hasNext(), is(false));
    }
}