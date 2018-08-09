package ru.job4j.generic;

/**
 * Role Store.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 09.08.2018.
 */
public class RoleStore<T extends Role> extends AbstractStore<T> {

    /**
     * Constructor RoleStore.
     *
     * @param size size new list of Role.
     */
    public RoleStore(int size) {
        super(size);
    }
}
