package ru.job4j.tree;

import java.util.Optional;

/**
 * Interface Simple Tree.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 18.08.2018.
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Add element child in parent.
     * Parent may has list child.
     * @param parent parent.
     * @param child child.
     * @return result (true if successfully - else false).
     */
    boolean add(E parent, E child);

    /**
     * Find Node by value.
     *
     * @param value value;
     * @return empty if tree has not value - else value.
     */
    Optional<Node<E>> findBy(E value);
}