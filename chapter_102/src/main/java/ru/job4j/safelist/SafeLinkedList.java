package ru.job4j.safelist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleLinkedList;
import java.util.Iterator;

/**
 * Thread Safe Simple Linked list.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 26.08.2018.
 */
@ThreadSafe
public class SafeLinkedList<E> implements Iterable<E> {

    /**
     * Contains list.
     *
     */
    @GuardedBy("this")
    private SimpleLinkedList<E> list;

    /**
     * Constructor to create list.
     *
     */
    public SafeLinkedList() {
        this.list = new SimpleLinkedList<>();
    }

    /**
     * Add new value in the list.
     *
     * @param value new value.
     */
    public synchronized void add(E value) {
       this.list.add(value);
    }

    /**
     * Get value by index
     *
     * @param index index of value.
     * @return value.
     * @throws IndexOutOfBoundsException if the list hasn't this index.
     */
    public synchronized E get(int index) {
        return this.list.get(index);
    }

    /**
     * Delete value in Linked list.
     *
     * @param index index value.
     * @return value that is deleted.
     */
    public synchronized E delete(int index) {
        return this.list.delete(index);
    }

    /**
     * Get size the list.
     *
     * @return size.
     */
    public synchronized int size() {
        return this.list.size();
    }

    /**
     * Iterator for the list.
     *
     * @return iterator type E.
     */
    @Override
    public synchronized Iterator<E> iterator() {
        SimpleLinkedList<E> clone = new SimpleLinkedList<>();
        for (E value: this.list) {
            clone.add(value);
        }
        return clone.iterator();
    }
}