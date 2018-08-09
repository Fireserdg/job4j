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
     * Constructor AbstractStore.
     *
     * @param size size new list.
     */
    public AbstractStore(int size) {
        this.list = new SimpleArray<>(size);
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
        int index = 0;
        for (E base:list) {
            if (base.getId().equals(id)) {
                result = true;
                list.set(index, model);
                break;
            }
            index++;
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
        int index = 0;
        for (E base:list) {
            if (base != null && base.getId().equals(id)) {
                list.delete(index);
                result = true;
                break;
            }
            index++;
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
        for (E base: list) {
            if (base != null && base.getId().equals(id)) {
                item = base;
                break;
            }
        }
        if (item == null) {
            throw new NoSuchElementException();
        }
        return item;
    }
}
