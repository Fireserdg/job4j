package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Simple Stack.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 13.08.2018.
 */
public class SimpleStack<T> {

    /**
     * Contains list for use method class SingleLinkedList.
     *
     */
    private SingleLinkedList<T> list = new SingleLinkedList<>();

    /**
     * Delete value in Stack.
     *
     * @return value that is deleted.
     */
    public T poll() {
        if (getSize() == 0) {
            throw new NoSuchElementException();
        }
        return this.list.delete();
    }

    /**
     * Push new value.
     *
     * @param value new value in Stack.
     */
    public void push(T value) {
        this.list.add(value);
    }

    /**
     * Get size Stack.
     *
     * @return size.
     */
    public int getSize() {
        return this.list.getSize();
    }
}
