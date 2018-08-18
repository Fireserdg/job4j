package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Node.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 18.08.2018.
 */
public class Node<T extends Comparable<T>> {

    /**
     * Contains list of children.
     *
     */
    private final List<Node<T>> children = new ArrayList<>();

    /**
     * Contains value.
     *
     */
    private final T value;

    /**
     * Constructor Node.
     *
     * @param value new value.
     */
    public Node(final T value) {
        this.value = value;
    }

    /**
     * Add new child in Tree.
     *
     * @param child new child.
     */
    public void add(Node<T> child) {
        this.children.add(child);
    }

    /**
     * Get list of children.
     *
     * @return list of children.
     */
    public List<Node<T>> leaves() {
        return this.children;
    }

    /**
     * Check equals two values.
     *
     * @param that that values.
     * @return result of comparison.
     */
    public boolean eqValues(T that) {
        return this.value.compareTo(that) == 0;
    }

    /**
     * Get value.
     *
     * @return the value.
     */
    public T getValue() {
        return this.value;
    }
}