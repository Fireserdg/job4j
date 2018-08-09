package ru.job4j.generic;

/**
 * User Store.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 09.08.2018.
 */
public class UserStore<T extends User> extends AbstractStore<T> {

    /**
     * Constructor UserStore.
     *
     * @param size size new list of User.
     */
    public UserStore(int size) {
        super(size);
    }
}
