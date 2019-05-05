package ru.job4j.beans.java;

import ru.job4j.beans.entity.User;
import ru.job4j.beans.service.UserService;
import ru.job4j.beans.storage.Storage;

/**
 * UserServiceAnnotationImpl
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-05-02
 */
public class UserServiceJavaConfig implements UserService {

    /**
     * Storage
     */
    private final Storage storage;

    /**
     * Constructor
     *
     * @param storage storage
     */
    public UserServiceJavaConfig(final Storage storage) {
        this.storage = storage;
    }

    /**
     * Add user
     *
     * @param user user
     */
    @Override
    public void add(User user) {
        storage.add(user);
    }

    /**
     * Find user by id
     *
     * @param id user id
     * @return user
     */
    @Override
    public User findUserById(Long id) {
        return storage.findUserById(id);
    }

}