package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;

/**
 * Simple Set.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 13.08.2018.
 */
public class SimpleSet<E> implements Iterable<E> {

    /**
     * Contains container.
     *
     */
    private SimpleArrayList<E> container;

    /**
     * Constructor to create Set with the given size.
     *
     * @param size size new Set.
     */
    public SimpleSet(int size) {
        this.container = new SimpleArrayList<>(size);
    }

    /**
     * Add new value in the container.
     *
     * @param e new value.
     */
    public void add(E e) {
        boolean result = false;
        for (E value: this.container) {
            if (!(value == null) && value.equals(e)) {
                result = true;
            }
        }
        if (!result) {
            this.container.add(e);
        }
    }

    /**
     * Get size the container.
     *
     * @return size.
     */
    public int getSize() {
        return this.container.getSize();
    }

    /**
     * Iterator for the Set.
     *
     * @return iterator type<E>.
     */
    @Override
    public Iterator<E> iterator() {
        return this.container.iterator();
    }
}