package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Simple Array List.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 11.08.2018.
 */
public class SimpleArrayList<E> implements Iterable<E> {

    /**
     * Contains container.
     *
     */
    private Object[] container;

    /**
     * Contains default size of container.
     *
     */
    private static final int DEFAULT_SIZE_CONTAINER = 10;

    /**
     * Contains the counter.
     *
     */
    private int count;

    /**
     * Contains a count of changes in the structure of container.
     *
     */
    private int modCount;


    /**
     * Constructor to create container with default size.
     *
     */
    public SimpleArrayList() {
        this.container = new Object[DEFAULT_SIZE_CONTAINER];
    }

    /**
     * Constructor to create container with the given size.
     *
     * @param size size new container.
     */
    public SimpleArrayList(int size) {
        this.container = new Object[size];
    }

    /**
     * Add new value in the container.
     *
     * @param value new value.
     */
    public void add(E value) {
        checkLengthContainer();
        this.container[count++] = value;
        this.modCount++;
    }

    /**
     * Get value by index
     *
     * @param index index of value.
     * @return value.
     * @throws IndexOutOfBoundsException, if the container hasn't this index.
     */
    public E get(int index) {
        checkIndex(index);
        return (E) container[index];
    }

    /**
     * Check Index in the container.
     *
     * @param index index cell in the container.
     */
    private void checkIndex(int index) {
        if (index < 0 || index > this.container.length - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Get size the container.
     *
     * @return size.
     */
    public int getSize() {
        return this.container.length;
    }

    /**
     * Check the container length. If the container length is equal to the count,
     * then increase the size of the container.
     *
     */
    private void checkLengthContainer() {
        if (this.count == this.container.length) {
            Object[] listNew = new Object[this.container.length * 2];
            System.arraycopy(this.container, 0, listNew, 0, this.container.length);
            this.container = listNew;
        }
    }

    /**
     * Iterator for the container.
     *
     * @return iterator type<E>.
     */
    @Override
    public Iterator<E> iterator() {
        return new IteratorArrayList((E[]) this.container);
    }

    /**
     * Inner class IteratorArrayList
     *
     */
    private class IteratorArrayList implements Iterator<E> {

        /**
         * Contains the values of the change counter at the time
         * the object was created.
         *
         */
        private int expectedModCount;

        /**
         * Contains list.
         *
         */
        private E[] list;

        /**
         * Contains index for get value in the container.
         *
         */
        private int index = 0;

        /**
         * Constructor Iterator ArrayList.
         *
         * @param list the container.
         */
        public IteratorArrayList(E[] list) {
            this.expectedModCount = modCount;
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            if (this.expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return this.index < this.list.length;
        }

        @Override
        public E next() {
            if (!(hasNext())) {
                throw new NoSuchElementException();
            }
            return this.list[index++];
        }
    }
}