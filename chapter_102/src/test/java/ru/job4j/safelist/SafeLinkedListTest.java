package ru.job4j.safelist;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Safe Linked List.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 26.08.2018.
 */
public class SafeLinkedListTest {

    /**
     * Contains list.
     *
     */
    private SafeLinkedList<String> list;

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
        list = new SafeLinkedList<>();
        first = new Thread(new Runnable() {
            @Override
            public void run() {
                list.add("One");
                list.add("Two");
                list.add("Three");
                list.add("Four");
            }
        });
    }

    @Test
    public void when3ElementsInListThenSize6() throws InterruptedException {
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                list.add("Five");
                list.add("Six");
                list.add("Seven");
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(list.size(), is(7));
    }

    @Test
    public void whenDeleteItemByListSecondThread() throws InterruptedException {
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add("Five");
                list.delete(0);
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(list.size(), is(4));
        assertThat(list.get(0), is("Two"));
        assertThat(list.get(3), is("Five"));
    }

    @Test
    public void whenGetItemByListByUsingIteratorThenLastHasNextFalse() throws InterruptedException {
        first.start();
        Thread.sleep(50);
        Iterator<String> iterator = list.iterator();
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 4; i++) {
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