package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * @param params values of user.
     * @return msg about add user.
     */
    public String add(final String[] params) {
        User result = this.store.add(new User(String.valueOf(
                this.counts.getAndIncrement()), params[0],
                params[1], params[1], System.currentTimeMillis()));
        if (result == null) {
            LOG.info(String.format(Message.MSG_ADD, params[0]));
            return String.format(Message.MSG_ADD, params[0]);
        }
        LOG.error(String.format(Message.MSG_EXIST, params[0]));
        throw new UserNotFoundException(String.format(Message.MSG_EXIST, params[0]));

    }

    /**
     * Update user by id.
     * @param params parameters for update.
     * @return msg about update user.
     */
    public String update(String[] params) {
        User oldUser = this.store.findById(params[0]);
        if (oldUser != null) {
            this.store.update(new User(oldUser.getId(),
                    () -> params[1].equals("") ? oldUser.getName() : params[1],
                    () -> params[2].equals("") ? oldUser.getLogin() : params[2],
                    () -> params[3].equals("") ? oldUser.getEmail() : params[3],
                    oldUser.getCreateDate()));
            LOG.info(String.format(Message.MSG_UPDATE, params[0]));
            return String.format(Message.MSG_UPDATE, params[0]);
        }
        LOG.error(String.format(Message.MSG_NOT_EXIST, params[0]));
        throw new UserNotFoundException(String.format(Message.MSG_NOT_EXIST, params[0]));
    }

    /**
     * Delete user by id.
     * @param id user id
     */
    public String delete(final String id) {
        if (this.store.findById(id) != null) {
            this.store.delete(id);
            LOG.info(String.format(Message.MSG_DELETE, id));
            return String.format(Message.MSG_DELETE, id);
        }
        LOG.info(String.format(Message.MSG_NOT_EXIST, id));
        throw new UserNotFoundException(String.format(Message.MSG_NOT_EXIST, id));
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