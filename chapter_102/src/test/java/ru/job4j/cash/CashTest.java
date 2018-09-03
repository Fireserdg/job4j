package ru.job4j.cash;

import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Non blocking cash.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 03.09.2018.
 */
public class CashTest {

    /**
     * Contains cash.
     *
     */
    private Cash cash;

    /**
     * Create cash for test methods.
     *
     */
    @Before
    public void createCash() {
        this.cash = new Cash();
    }

    @Test
    public void whenAddBaseMainThreadThenDeleteAndGetSize() {
        assertThat(cash.add(new Base(1, "Fedor")), is(true));
        assertThat(cash.add(new Base(2, "Vasya")), is(true));
        assertThat(cash.add(new Base(3, "Petr")), is(true));
        assertThat(cash.add(new Base(4, "Alex")), is(true));
        assertThat(cash.add(new Base(4, "Alex")), is(false));
        assertThat(cash.update(new Base(3, "Hulk")), is(true));
        assertThat(cash.delete(new Base(1, "Fedor")), is(true));
        assertThat(cash.delete(new Base(4, "Alex")), is(true));
        assertThat(cash.delete(new Base(5, "Hulk")), is(false));
        assertThat(cash.size(), is(2));
    }

    @Test
    public void whenCreate5ModelInMainThreadThenUpdateOneModel()
            throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        cash.add(new Base(1, "Fedor"));
        cash.add(new Base(2, "Vasya"));
        cash.add(new Base(3, "Petr"));
        cash.add(new Base(4, "Alex"));
        cash.add(new Base(5, "Fedor"));
        Thread first = new Thread(() -> {
            try {
                cash.update(new Base(5, "Mark"));
            } catch (Exception e) {
                ex.set(e);
            }
        });
        Thread second = new Thread(() -> {
                try {
                    cash.update(new Base(5, "Malik"));
                } catch (Exception e) {
                    ex.set(e);
                }

        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(cash.get(5).getVersion(), is(1));
        assertThat(ex.get().getMessage(), is("Optimistic Exception"));
    }
}