package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Iterator for Even values.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 06.08.2018.
 */
public class EventIt implements Iterator<Integer> {

    /**
     * Contains value.
     */
    private int[] value;

    /**
     * Contains index for element.
     */
    private int index = 0;

    /**
     * Constructor object EventIt.
     *
     * @param value new value.
     */
    public EventIt(int[] value) {
        this.value = value;
    }

    /**
     * Checks possible increment of the index.
     *
     * @return booleans value.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < value.length; i++) {
            if (value[i] % 2 == 0) {
                result = true;
                index = i;
                break;
            }
        }
        return result;
    }

    /**
     * Pointer to the element.
     *
     * @return values.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return value[index++];
    }
}
