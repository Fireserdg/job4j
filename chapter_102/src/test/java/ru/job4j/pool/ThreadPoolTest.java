package ru.job4j.pool;

import org.junit.Test;
import java.util.Random;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Thread pool.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 04.09.2018.
 */
public class ThreadPoolTest {
    /**
     * Contains count for check have done job.
     */
    private int count = 0;

    /**
     * Increment count.
     *
     */
    public synchronized void setIncrement() {
        count++;
    }

    @Test
    public void whenGetJobThenStartThreadPool() {
        ThreadPool pool = new ThreadPool();
        for (int i = 0; i < 50_000; i++) {
            pool.work(() -> {
                Random random = new Random();
                for (int j = 0; j < 50; j++) {
                    Math.sqrt(random.nextInt(100));
                }
                setIncrement();
            });
        }
        pool.shutDown();
        assertThat(count, is(50000));
    }
}