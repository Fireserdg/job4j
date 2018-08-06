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
    private int index = 0;

    /**
     * Contains internal index.
     */
    private int indexIn = 0;

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
        return index < value[indexIn].length;
    }

    /**
     * Pointer to the element.
     *
     * @return values.
     */
    @Override
    public Integer next() {
        int result;
        try {
            result = value[indexIn][index++];
            if (!(hasNext()) && indexIn < value.length - 1) {
                indexIn++;
                index = 0;
            }
        } catch (ArrayIndexOutOfBoundsException aob) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
