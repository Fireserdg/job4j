package ru.job4j.beans.java;

import ru.job4j.beans.entity.User;
import ru.job4j.beans.storage.Storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Memory storage
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-05-02
 */
public class MemoryStorageJavaConfig implements Storage {

    /**
     * Storage
     */
    private final Map<Long, User> storage = new ConcurrentHashMap<>();

    /**
     * Identifier
     */
    private final AtomicLong id = new AtomicLong();

    /**
     * Add user
     *
     * @param user user
     */
    @Override
    public void add(User user) {
        if (user.getId() == null) {
            final Long userId = id.incrementAndGet();
            user.setId(userId);
            storage.put(userId, user);
        }
    }

    /**
     * Find user by id
     *
     * @param id user id
     * @return user
     */
    @Override
    public User findUserById(Long id) {
        return storage.get(id);
    }
}
