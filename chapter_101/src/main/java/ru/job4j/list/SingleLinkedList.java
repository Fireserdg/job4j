package ru.job4j.list;

/**
 * Сlass SingleLinkedList.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 10.08.2018.
 */
public class SingleLinkedList<E> {

    /**
     * Contains size list.
     *
     */
    private int size;

    /**
     * Contains link to first object.
     *
     */
    private Node<E> first;

    /**
     * Add date in the beginning of list.
     *
     * @param date new date.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Delete first date.
     *
     * @return removed date.
     */
    public E delete() {
        E result = this.first.date;
        this.first = this.first.next;
        size--;
        return result;
    }

    /**
     * Get date by index.
     *
     * @param index index date.
     * @return date.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Get size of the list.
     *
     * @return size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Class for contains date.
     *
     * @param <E> type date.
     */
    private static class Node<E> {

        /**
         * Contains date.
         *
         */
        E date;

        /**
         * Contains link to next date.
         *
         */
        Node<E> next;

        /**
         * Constructor Node.
         *
         * @param date new date.
         */
        Node(E date) {
            this.date = date;
        }
    }
}
