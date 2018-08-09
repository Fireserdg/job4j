package ru.job4j.generic;

import java.util.Iterator;

/**
 * Simple Array for data storage.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.08.2018.
 */
public class SimpleArray<T> implements Iterable<T> {

    /**
     * Contains object Array.
     *
     */
    private final T[] array;

    /**
     * Contains value size.
     */
    private int size;

    /**
     * Contains value count.
     */
    private int count = 0;

    /**
     * Constructor object SimpleArray.
     *
     * @param size new SimpleArray.
     */
    public SimpleArray(int size) {
        this.size = size;
        this.array = (T[]) new Object[this.size];
    }

    /**
     * Add new object type <T> in array.
     *
     * @param model object type <T>.
     * @throws ArrayIndexOutOfBoundsException(), If there is no space in the array
     */
    public void add(T model) {
        if (this.count == size) {
            throw new ArrayIndexOutOfBoundsException(String.format("Size: %s", size));
        }
        array[this.count++] = model;
    }

    /**
     * Set new value for array cell.
     *
     * @param index index item in array.
     * @param model new object type <T>.
     * @throws IndexOutOfBoundsException, if the array hasn't this index.
     */
    public void set(int index, T model) {
        checkIndexInArray(index);
        array[index] = model;
    }

    /**
     * Delete item by index.
     *
     * @param index index cell in array.
     * @throws IndexOutOfBoundsException, if the array hasn't this index.
     */
    public void delete(int index) {
        checkIndexInArray(index);
        if (index != size - 1) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
            array[size - 1] = null;
            count--;
        } else {
            array[index] = null;
            count--;
        }
    }

    /**
     * Delete item by index.
     *
     * @param index index cell in array.
     * @return object type <T>.
     * @throws IndexOutOfBoundsException, if the array hasn't this index.
     */
    public T get(int index) {
        checkIndexInArray(index);
        return array[index];
    }

    /**
     * Check Index in the array
     *
     * @param index index cell in the array.
     */
    private void checkIndexInArray(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Iterator for array.
     *
     * @return elements in the array.
     */
    @Override
    public Iterator<T> iterator() {
        return new IteratorSimpleArray<>(array);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int value = 0;
        sb.append("[");
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            value++;
            if (t != null && iterator.hasNext()) {
                sb.append(t);
            }
            if (iterator.hasNext() && array[value] != null) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}