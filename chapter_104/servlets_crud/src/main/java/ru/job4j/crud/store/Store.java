package ru.job4j.crud.store;

import ru.job4j.crud.models.User;

import java.util.List;
import java.util.Map;

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
     * @param user new Role.
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

    /**
     * Get country
     *
     * @return map of country
     */
    Map<String, String> getCountry();

    /**
     * Get city
     *
     * @param id country id
     * @return list of country
     */
    List<String> getCity(String id);
}