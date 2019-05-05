package ru.job4j.beans.service;

import ru.job4j.beans.entity.User;

/**
 * User Service
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-05-04
 */
public interface UserService {

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
