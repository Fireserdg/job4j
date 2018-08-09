package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for Simple Array.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.08.2018.
 */
public class IteratorSimpleArray<E> implements Iterator<E> {

    /**
     * Contains array type E.
     *
     */
    private E[] array;

    /**
     * Contains value count.
     */
    private int count = 0;

    /**
     * Constructor object SimpleArray.
     *
     * @param array new Array.
     */
    public IteratorSimpleArray(E[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return count < this.array.length;
    }

    @Override
    public E next() {
        if (!(hasNext())) {
            throw new NoSuchElementException();
        }
        return this.array[count++];
    }
}
