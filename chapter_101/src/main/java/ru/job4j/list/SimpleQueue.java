package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Simple Queue.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 13.08.2018.
 */
public class SimpleQueue<T> {

    /**
     * Contains list for use method class SimpleLinkedList.
     *
     */
    private SimpleLinkedList<T> list = new SimpleLinkedList<>();

    /**
     * Delete value in Queue.
     *
     * @return value that is deleted.
     */
    public T poll() {
        if (getSize() == 0) {
            throw new NoSuchElementException();
        }
        return this.list.delete(0);
    }

    /**
     * Push value in Queue.
     *
     * @param value new value
     */
    public void push(T value) {
        this.list.add(value);
    }

    /**
     * Get size in Queue.
     *
     * @return size.
     */
    public int getSize() {
        return this.list.size();
    }
}