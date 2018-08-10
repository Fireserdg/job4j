package ru.job4j.generic;

import java.util.NoSuchElementException;

/**
 * Abstract class for UserStore and RoleStore.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 09.08.2018.
 */
public abstract class AbstractStore<E extends Base> implements Store<E> {

    /**
     * Contains list type <E>.
     *
     */
    private SimpleArray<E> list;

    /**
     * Contains size list.
     *
     */
    private int size;
    /**
     * Constructor AbstractStore.
     *
     * @param size size new list.
     */
    public AbstractStore(int size) {
        this.size = size;
        this.list = new SimpleArray<>(this.size);
    }

    /**
     * Add new item.
     *
     * @param model new item type <E>.
     */
    public void add(E model) {
        this.list.add(model);
    }

    /**
     * Replace item.
     *
     * @param id id old item.
     * @param model new item type <E>.
     * @return result operations (true or false).
     */
    public boolean replace(String id, E model) {
        boolean result = false;
        for (int i = 0; i < this.size; i++) {
            if (this.list.get(i).getId().equals(id)) {
                this.list.set(i, model);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Delete item.
     *
     * @param id id item.
     * @return result operations (true or false).
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < this.size; i++) {
            if (this.list.get(i).getId().equals(id)) {
                this.list.delete(i);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Find item by id.
     *
     * @param id item id.
     *
     * @return item.
     */
    public E findById(String id) {
        E item = null;
        for (E base: this.list) {
            if (base != null && base.getId().equals(id)) {
                item = base;
                break;
            }
        }
        return item;
    }
}
