package ru.job4j.map;

import java.util.*;

/**
 * Simple HashMap.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 14.08.2018.
 */
public class SimpleHashMap<K, V> implements Iterable {

    /**
     * Contains table for storage key-value.
     *
     */
    private Entry[] table;

    /**
     * Contains default size table.
     *
     */
    private static final int DEFAULT_SIZE_TABLE = 16;

    /**
     * Count insert items.
     *
     */
    private int count;

    /**
     * Count modification in table.
     *
     */
    private int modCount;

    /**
     * Constructor with default size table.
     *
     */
    public SimpleHashMap() {
        this.table = new Entry[DEFAULT_SIZE_TABLE];
    }

    /**
     * Constructor.
     *
     * @param size size table
     */
    public SimpleHashMap(int size) {
        this.table = new Entry[size];
    }

    /**
     * Insert new item by key-value.
     *
     * @param key key.
     * @param value value.
     * @return true if item insert table else false.
     */
    public boolean insert(K key, V value) {
        boolean result;
        checkSize(this.table.length);
        if (this.table[position(key)] != null) {
            result = false;
        } else {
            this.table[position(key)] = new Entry<>(key, value);
            result = true;
            count++;
            modCount++;
        }
        return result;
    }

    /**
     * Get value by key.
     *
     * @param key key.
     * @return value.
     */
    public V get(K key) {
        V value = null;
        if (this.table[position(key)].getKey().equals(key)) {
            value = (V) this.table[position(key)].getValue();
        }
        return value;
    }

    /**
     * Delete item by key.
     *
     * @param key key.
     * @return true if item delete else false.
     */
    public boolean delete(K key) {
        boolean result = false;
        if (this.table[position(key)] != null) {
            this.table[position(key)] = null;
            result = true;
            count--;
            modCount++;
        }
        return result;
    }

    /**
     * Get size the table.
     *
     * @return size.
     */
    public int getSize() {
        return this.table.length;
    }

    /**
     * Get position item in table by hash code.
     *
     * @param key item's key
     * @return position in table.
     */
    private int position(K key) {
        return  (key.hashCode()) & (table.length - 1);
    }

    /**
     * Check size table.
     *
     * @param size table length.
     */
    private void checkSize(int size) {
        if (size == count) {
            Entry[] newTable = new Entry[size * 2];
            System.arraycopy(this.table, 0, newTable, 0, size);
            this.table = newTable;
            modCount++;
        }
    }

    /**
     * Iterator for Entry.
     *
     * @return new Iterator for SimpleHashMap.
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new IteratorSimpleHashMap(this.table, this.modCount);
    }

    /**
     * Class for storage items.
     *
     * @param <K> key.
     * @param <V> value.
     */
    static class Entry<K, V> {

        /**
         * Contains key the item.
         *
         */
        private K key;

        /**
         * Contains value the item.
         *
         */
        private V value;

        /**
         * Constructor.
         *
         * @param key key item.
         * @param value value item.
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    /**
     * Iterator for Simple HashMap.
     *
     */
    private class IteratorSimpleHashMap implements Iterator<Entry<K, V>> {

        /**
         * Contains table.
         *
         */
        private Entry<K, V>[] table;

        /**
         * Contains index position in table.
         *
         */
        private int index = 0;

        /**
         * Counter get items(Entry in table).
         *
         */
        private int counter;

        /**
         * Contains the values of the change counter at the time
         * the object was created.
         *
         */
        private int expectedNodCount;

        /**
         * Constructor Iterator.
         *
         * @param table table.
         * @param expected expectedNodCount.
         */
        public IteratorSimpleHashMap(Entry<K, V>[] table, int expected) {
            this.table = table;
            this.expectedNodCount = expected;
        }

        @Override
        public boolean hasNext() {
            if (this.expectedNodCount != modCount) {
                throw new ConcurrentModificationException();
            }
            boolean result = this.index < this.table.length && this.counter != count;
            while (result && this.table[index] == null) {
                index++;
            }
            return result;
        }

        @Override
        public Entry<K, V> next() {
            if (!(hasNext())) {
                throw new NoSuchElementException();
            }
            this.counter++;
            return this.table[index++];
        }
    }
}