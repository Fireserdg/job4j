package ru.job4j.set;

import ru.job4j.list.SimpleLinkedList;
import java.util.Iterator;

/**
 * Linked Set.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 13.08.2018.
 */
public class LinkedSet<E> implements Iterable<E> {

    /**
     * Contains container.
     *
     */
    private SimpleLinkedList<E> list;

    /**
     * Constructor to create Set with the given size.
     *
     */
    public LinkedSet() {
        this.list = new SimpleLinkedList<>();
    }
    /**
     * Add new value in the container.
     *
     * @param e new value.
     */
    public void add(E e) {
        boolean result = false;
        for (E value: this.list) {
            if (!(value == null) && value.equals(e)) {
                result = true;
            }
        }
        if (!result) {
            this.list.add(e);
        }
    }

    /**
     * Get size the container.
     *
     * @return size.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Iterator for the Set.
     *
     * @return iterator type E.
     */
    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }
}
