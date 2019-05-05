package ru.job4j.beans.storage;

import ru.job4j.beans.entity.User;

/**
 * Storage
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-05-02
 */
public interface Storage {

    /**
     * Add user
     *
     * @param user user
     */
    void add(User user);

    /**
     * Find user by id
     *
     * @param id user id
     * @return user
     */
    User findUserById(Long id);
}
