package ru.job4j.safelist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;

/**
 * Thread Safe Simple Array list.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 26.08.2018.
 */
@ThreadSafe
public class SafeArrayList<E> implements Iterable<E> {

    /**
     * Contains list.
     *
     */
    @GuardedBy("this")
    private SimpleArrayList<E> list;

    /**
     * Constructor to create list with default size.
     *
     */
    public SafeArrayList() {
        this.list = new SimpleArrayList<>();
    }

    /**
     * Constructor to create list with the given size.
     *
     * @param size size new list.
     */
    public SafeArrayList(int size) {
        this.list = new SimpleArrayList<>(size);
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
     * Get size the list.
     *
     * @return size.
     */
    public synchronized int size() {
        return this.list.getSize();
    }

    /**
     * Iterator for the list.
     *
     * @return iterator type E.
     */
    @Override
    public synchronized Iterator<E> iterator() {
        return this.list.iterator();
    }
}