package ru.job4j.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Memory Store.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 10.11.18
 */
public class MemoryStore implements Store<User> {

    /**
     * Instance class MemoryStore.
     */
    private static final MemoryStore OUR_INSTANCE = new MemoryStore();

    /**
     * Container for users.
     */
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    /**
     * Get instance.
     * @return instance.
     */
    public static MemoryStore getInstance() {
        return OUR_INSTANCE;
    }

    /**
     * Private constructor.
     */
    private MemoryStore() {
    }

    /**
     * Add new user.
     * @param user new User.
     * @return true if user was added.
     */
    @Override
    public User add(final User user) {
        return this.users.putIfAbsent(Integer.valueOf(user.getId()), user);
    }

    /**
     * Update user.
     * @param user new data of user.
     */
    @Override
    public void update(final User user) {
        this.users.computeIfPresent(Integer.parseInt(user.getId()),
                (k, v) -> user);
    }

    /**
     * Delete user by id.
     * @param id user id
     */
    @Override
    public void delete(final String id) {
        this.users.remove(Integer.parseInt(id));
    }

    /**
     * Find all in the container.
     * @return container for user.
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.users.values());
    }

    /**
     * Find user by id.
     * @param id user id.
     * @return true if user exist.
     */
    @Override
    public User findById(final String id) {
        return this.users.get(Integer.parseInt(id));
    }
}
