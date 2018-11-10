package ru.job4j.crud;

import java.util.List;

/**
 * Store.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 03.11.18
 */
public interface Store<T> {

    /**
     * Add new user.
     * @param user new User.
     * @return true if user was added.
     */
    T add(final User user);

    /**
     * Update user.
     * @param user new data of user.
     */
    void update(final User user);

    /**
     * Delete user by id.
     * @param id user id
     */
    void delete(final String id);

    /**
     * Find all in the container.
     * @return container users.
     */
    List<T> findAll();

    /**
     * Find user by id.
     * @param id user id.
     * @return true if user exist.
     */
    T findById(final String id);
}