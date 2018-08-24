package ru.job4j.count;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Simple Count.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 24.08.2018.
 */
@ThreadSafe
public class Count {
    /**
     * Contains value.
     *
     */
    @GuardedBy("this")
    private int value;

    /**
     * An increment in the value.
     *
     */
    public synchronized void increment() {
        this.value++;
    }

    /**
     * Get value.
     *
     * @return value.
     */
    public synchronized int get() {
        return this.value;
    }
}
