package ru.job4j.crud.store;

import ru.job4j.crud.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
    private final Map<String, User> users = new ConcurrentHashMap<>();

    /**
     * Container for country
     */
    private final Map<String, String> country = new ConcurrentHashMap<>();

    /**
     * Container for city
     */
    private final Map<String, List<String>> city = new ConcurrentHashMap<>();

    /**
     * Count for get id.
     */
    private final AtomicInteger counts = new AtomicInteger(1);

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
        init();
    }

    /**
     * Add new user.
     * @param user new Role.
     * @return true if user was added.
     */
    @Override
    public User add(final User user) {
        return this.users.computeIfAbsent(String.valueOf(
                this.counts.getAndIncrement()), k -> {
            user.setId(k);
            return user;
        });
    }

    /**
     * Update user.
     * @param user new data of user.
     */
    @Override
    public void update(final User user) {
        this.users.computeIfPresent(user.getId(),
                (k, v) -> user);
    }

    /**
     * Delete user by id.
     * @param id user id
     */
    @Override
    public void delete(final String id) {
        this.users.remove(id);
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
        return this.users.get(id);
    }

    /**
     * Get country
     *
     * @return map of country
     */
    @Override
    public Map<String, String> getCountry() {
        return this.country;
    }

    /**
     * Get city
     *
     * @param id country id
     * @return return list of city
     */
    @Override
    public List<String> getCity(String id) {
        return new ArrayList<>(this.city.get(id));
    }

    /**
     * Init coutry and city
     *
     */
    private void init() {
        country.put("1", "Russia");
        country.put("2", "USA");
        country.put("3", "France");
        city.put("1", Arrays.asList("Moscow", "Tver", "Kazan", "Voronezh"));
        city.put("2", Arrays.asList("Denver", "Boston", "New-York"));
        city.put("3", Arrays.asList("Paris", "Marcel", "Bordo", "Lion"));
    }
}