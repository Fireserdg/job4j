package ru.job4j.blocking;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple blocking queue.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 27.08.2018.
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    /**
     * Contains queue.
     *
     */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    /**
     * Contains max size for blocking queue.
     *
     */
    private final int limit;

    /**
     * Constructor to create list with the given size.
     *
     * @param size size new list.
     */
    public SimpleBlockingQueue(int size) {
        this.limit = size;
    }

    /**
     * Add new value in the queue.
     *
     * @param value new value.
     * @throws InterruptedException if thread is interrupted.
     */
    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() == limit) {
                this.wait();
            }
            queue.offer(value);
            this.notify();
        }
    }

    /**
     * Get and delete first value in the blocking queue.
     *
     * @return value.
     * @throws InterruptedException if thread is interrupted.
     */
    public T pool() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                this.wait();
            }
            T value = queue.poll();
            this.notify();
            return value;
        }
    }

    /**
     * Get size
     *
     * @return size blocking queue.
     */
    public synchronized int size() {
        return this.queue.size();
    }
}