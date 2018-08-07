package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for Even values.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 06.08.2018.
 */
public class Converter {

    /**
     * Convert several Iterators to one.
     *
     * @param it several Iterators
     * @return new Iterator with all the values.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> iterator = it.next();
            /**
             * Checks possible increment.
             *
             * @return booleans value.
             */
            @Override
            public boolean hasNext() {
                boolean result = this.iterator.hasNext();
                while (!result && it.hasNext()) {
                    this.iterator = it.next();
                    result = this.iterator.hasNext();
                    if (result) {
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
                int result;
                if (this.hasNext()) {
                    result = this.iterator.next();
                } else {
                    throw new NoSuchElementException();
                }
                if (!(this.iterator.hasNext()) && it.hasNext()) {
                    this.iterator = it.next();
                }
                return result;
            }
        };
    }
}
