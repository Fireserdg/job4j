package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Iterator for Matrix array.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 06.08.2018.
 */
public class Matrix implements Iterator<Integer> {

    /**
     * Contains value.
     */
    private final int[][]  value;

    /**
     * Contains index for element.
     */
    private int index;

    /**
     * Contains internal index.
     */
    private int indexIn;

    /**
     * Constructor object Matrix.
     *
     * @param value new value.
     */
    public Matrix(final int[][] value) {
        this.value = value;
    }

    /**
     * Checks possible increment of the index.
     *
     * @return booleans value.
     */
    @Override
    public boolean hasNext() {
        return value.length > 0 && value[indexIn].length > index;
    }

    /**
     * Pointer to the element.
     *
     * @return values.
     */
    @Override
    public Integer next() {
        if (!(hasNext())) {
            throw new NoSuchElementException();
        }
        int result = value[indexIn][index++];
        if (!(hasNext()) && indexIn < value.length - 1) {
            indexIn++;
            index = 0;
        }
        return result;
    }
}
