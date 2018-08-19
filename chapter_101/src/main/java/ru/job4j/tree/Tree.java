package ru.job4j.tree;

import java.util.*;

/**
 * Tree.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 18.08.2018.
 */
public class Tree<T extends Comparable<T>> implements SimpleTree<T> {

    /**
     * Contains root.
     *
     */
    private Node<T> root;

    private int modCount;

    /**
     * Add parent and child.
     *
     * @param parent parent.
     * @param child child.
     * @return result (true if successfully - else false).
     */
    @Override
    public boolean add(T parent, T child) {
        boolean result = false;
        if (this.root == null) {
            this.root = new Node<>(parent);
            this.root.add(new Node<>(child));
            result = true;
            this.modCount++;
        } else if (findBy(parent).isPresent()) {
            findBy(parent).get().add(new Node<>(child));
            result = true;
            this.modCount++;
        }
        return result;
    }

    /**
     * Find Node by value.
     *
     * @param value value;
     * @return empty if tree has not value - else value.
     */
    @Override
    public Optional<Node<T>> findBy(T value) {
        Optional<Node<T>> rsl = Optional.empty();
        Queue<Node<T>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<T> el = data.poll();
            if (el.eqValues(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<T> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Check the number of child element in the tree.
     *
     * @return true if tree is binary - else false.
     */
    public boolean isBinary() {
        boolean result = true;
        Queue<Node<T>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<T> element = data.poll();
            if (!(element.leaves().size() <= 2)) {
                result = false;
                break;
            }
            for (Node<T> child : element.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    /**
     * Iterator for the Tree.
     *
     * @return iterator type T.
     */
    @Override
    public Iterator<T> iterator() {
        return new IteratorTree(this.root, this.modCount);
    }

    /**
     * Iterator for Tree.
     *
     */
    private class IteratorTree implements Iterator<T> {

        /**
         * Contains queue from elements tree.
         *
         */
        private Queue<Node<T>> queue = new LinkedList<>();

        /**
         * Contains the values of the change counter at the time
         * the object was created.
         *
         */
        private int expectedModCount;

        /**
         * Constructor Iterator.
         *
         * @param root root.
         * @param modCount expectedModCount.
         */
        public IteratorTree(Node<T> root, int modCount) {
            this.queue.offer(root);
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (this.expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> item = this.queue.poll();
            for (Node<T> values : item.leaves()) {
                this.queue.offer(values);
            }
            return item.getValue();
        }
    }
}