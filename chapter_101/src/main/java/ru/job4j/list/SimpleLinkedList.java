package ru.job4j.list;

import java.util.*;

/**
 * Class Simple Linked list.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 12.08.2018.
 */
public class SimpleLinkedList<E> implements Iterable<E> {

    /**
     * Contains size of Linked list.
     *
     */
    private int size;

    /**
     * Contains link for first item.
     *
     */
    private Node<E> first;

    /**
     * Contains link for last item.
     *
     */
    private Node<E> last;

    /**
     * Contains a count of changes in the structure of Linked list.
     *
     */
    private int modCount;

    /**
     * Add new value in the Linked list.
     *
     * @param value new value.
     */
    public void add(E value) {
        Node<E> lastItem = this.last;
        Node<E> newItem = new Node<>(lastItem, value, null);
        this.last = newItem;
        if (lastItem == null) {
            this.first = newItem;
        } else {
            lastItem.next = newItem;
        }
        this.size++;
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
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Get size the Linked list.
     *
     * @return size.
     */
    public int size() {
        return this.size;
    }

    /**
     * Check Index in the Linked list.
     *
     * @param index index item in the Linked list.
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Iterator for the container.
     *
     * @return iterator type<E>.
     */
    @Override
    public Iterator<E> iterator() {
        return new IteratorLinkedList(this.first);
    }

    /**
     * Inner class Node for store links and object.
     *
     * @param <E> type object.
     */
    private static class Node<E> {

        /**
         * Contains date.
         *
         */
        E date;

        /**
         * Contains link for next object.
         *
         */
        Node<E> next;

        /**
         * Contains link for previous object.
         *
         */
        Node<E> previous;

        /**
         * Constructor Node.
         *
         * @param previous link for previous Node.
         * @param date new date.
         * @param next link for next Node.
         */
        Node(Node<E> previous, E date, Node<E> next) {
        this.date = date;
        this.next = next;
        this.previous = previous;
        }
    }

    /**
     * Inner class IteratorLinkedList.
     *
     */
    private class IteratorLinkedList implements Iterator<E> {

        /**
         * Contains the values of the change counter at the time
         * the object was created.
         *
         */
        private int expectedModCount;

        /**
         * Contains node for get next object Node<E>.
         *
         */
        private Node<E> node;

        /**
         * Constructor Iterator Linked List.
         *
         * @param e first item in Linked List.
         */
        public IteratorLinkedList(Node<E> e) {
            this.expectedModCount = modCount;
            this.node = e;

        }
        @Override
        public boolean hasNext() {
            if (this.expectedModCount != modCount) {
                throw new ConcurrentModificationException();

            }
            return node != null;
        }

        @Override
        public E next() {
            if (!(hasNext())) {
                throw new NoSuchElementException();
            }
            Node<E> result = node;
            this.node = node.next;
            return result.date;
        }
    }
}