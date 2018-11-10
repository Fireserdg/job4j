package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Validate Service
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 03.11.18
 */
public class ValidateService {
    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ValidateService.class);

    /**
     * Contains class instance.
     */
    private static final ValidateService VALIDATE = new ValidateService();

    /**
     * Contains storage.
     */
    private final Store<User> store = MemoryStore.getInstance();

    /**
     * Count for get id.
     */
    private final AtomicInteger counts = new AtomicInteger(1);

    /**
     * Constructor.
     */
    private ValidateService() {
    }

    /**
     * Get instance.
     * @return instance of ValidateService.
     */
    public static ValidateService getInstance() {
        return VALIDATE;
    }

    /**
     * Add user.
     * @param name user name.
     * @return true if user was added.
     */
    public User add(final String name) {
        User result = this.store.add(
                new User(String.valueOf(this.counts.getAndIncrement()),
                        name, String.format("login.%s", name),
                String.format("%s@gmail.com", name), System.currentTimeMillis()));
        if (result != null) {
            LOG.error("User already exist");
        } else {
            LOG.info(String.format("User with name=%s was successfully added",
                    name));
        }
        return result;
    }

    /**
     * Update user by id.
     * @param id user id.
     * @param name new name of user.
     */
    public User update(final String id, final String name) {
        User oldUser = this.store.findById(id);
        if (oldUser != null) {
            this.store.update(new User(oldUser.getId(),
                    name, oldUser.getLogin(),
                    oldUser.getEmail(), oldUser.getCreateDate()));
            LOG.info(String.format("User with id=%s was successfully updated", id));
        } else {
            LOG.error(String.format("User with id=%s does not exist", id));
        }
        return oldUser;
    }

    /**
     * Delete user by id.
     * @param id user id
     */
    public User delete(final String id) {
        User user = this.store.findById(id);
        if (user != null) {
            this.store.delete(id);
            LOG.info(String.format("User with id=%s was successfully delete", id));
        } else {
            LOG.info(String.format("User with id=%s does not exist", id));
        }
        return user;
    }

    /**
     * Find all in the container.
     * @return container for user.
     */
    public List<User> findAll() {
        return this.store.findAll();
    }

    /**
     * Find user by id.
     * @param id user id.
     * @return true if user exist.
     */
    public User findById(String id) {
        return this.store.findById(id);
    }
}