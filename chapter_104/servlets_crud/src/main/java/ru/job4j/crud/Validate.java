package ru.job4j.crud;

import ru.job4j.crud.models.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface validate.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 05.12.18
 */
public interface Validate {

    /**
     * Add user.
     * @param params values of user.
     * @return msg about add user.
     */
    String add(final String[] params);

    /**
     * Update user by id.
     * @param params parameters for update.
     * @return msg about update user.
     */
    String update(final String[] params);

    /**
     * Delete user by id.
     * @param id user id
     */
    String delete(final String id);

    /**
     * Find all in the container.
     * @return container for user.
     */
    List<User> findAll();

    /**
     * Find user by id.
     * @param id user id.
     * @return true if user exist.
     */
    User findById(String id);

    /**
     * Is Credentials.
     *
     * @param login user login
     * @param password user password
     * @return Optional for get result in Signings Servlet
     */
    Optional<User> isCredentials(String login, String password);

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
     * @return list of city
     */
    List<String> getCity(String id);

}