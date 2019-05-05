package ru.job4j.beans.xml;

import ru.job4j.beans.entity.User;
import ru.job4j.beans.service.UserService;
import ru.job4j.beans.storage.Storage;

/**
 *  User Service
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-05-02
 */
public class UserServiceXml implements UserService {

    /**
     * Storage
     */
    private final Storage storage;

    /**
     * Constructor
     *
     * @param storage storage
     */
    public UserServiceXml(Storage storage) {
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
