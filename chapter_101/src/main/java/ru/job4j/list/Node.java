package ru.job4j.list;

/**
 * Linked list Node.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 13.08.2018.
 */
public class Node<T> {

    /**
     * Contains value.
     *
     */
    final T value;

    /**
     * Contains link to next object.
     *
     */
    Node<T> next;

    /**
     * Constructor Node.
     *
     * @param value new value.
     */
    public Node(final T value) {
        this.value = value;
    }

    /**
     * Check has Cycle in Node.
     *
     * @param first object class Node.
     * @return true if has Cycle, else false.
     */
    public static boolean hasCycle(Node first) {
        boolean result = false;
        Node one = first;
        Node two = first;
        while (one != null && two != null) {
            one = one.next;
            two = two.next.next;
            if (one == two) {
                result = true;
                break;
            }
        }
        return result;
    }
}
