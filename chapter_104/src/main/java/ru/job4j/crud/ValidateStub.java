package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.models.Role;
import ru.job4j.crud.models.User;

import java.util.*;

/**
 * Validate Stub for test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 05.12.18
 */
public class ValidateStub implements Validate {
    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ValidateStub.class);

    /**
     * Container for users.
     */
    private final Map<String, User> store = new HashMap<>();

    /**
     * Count for get id.
     */
    private int id = 1;

    /**
     * Add user.
     * @param params values of user.
     * @return msg about add user.
     */
    @Override
    public String add(String[] params) {
        if (checkLogin(params[1])) {
            User user = new User(params[0], params[1],
                    params[2], params[3], System.currentTimeMillis(),
                    Role.valueOf(params[4]));
            user.setId(String.valueOf(id++));
            user = this.store.putIfAbsent(user.getId(), user);
            if (user == null) {
                LOG.info(String.format(Message.MSG_ADD, params[0]));
                return String.format(Message.MSG_ADD, params[0]);
            }
        }
        LOG.error(String.format(Message.MSG_EXIST, params[1]));
        throw new UserLoginException(String.format(Message.MSG_EXIST, params[1]));
    }

    /**
     * Update user by id.
     * @param params parameters for update.
     * @return msg about update user.
     */
    @Override
    public String update(String[] params) {
        User oldUser = this.findById(params[0]);
        if (checkLogin(params[2], oldUser.getId())) {
            this.store.computeIfPresent(oldUser.getId(), (k, v) -> new User(oldUser.getId(),
                    () -> params[1].equals("") ? oldUser.getName() : params[1],
                    () -> params[2].equals("") ? oldUser.getLogin() : params[2],
                    () -> params[3].equals("") ? oldUser.getPassword() : params[3],
                    () -> params[4].equals("") ? oldUser.getEmail() : params[4],
                    oldUser.getCreate(), Role.valueOf(params[5])));
            LOG.info(String.format(Message.MSG_UPDATE, params[1]));
            return String.format(Message.MSG_UPDATE, params[1]);
        }
        LOG.error(String.format(Message.MSG_EXIST, params[2]));
        throw new UserLoginException(String.format(Message.MSG_EXIST, params[2]));
    }

    /**
     * Delete user by id.
     * @param id user id
     */
    @Override
    public String delete(String id) {
        User user = this.findById(id);
        store.remove(user.getId());
        LOG.info(String.format(Message.MSG_DELETE, user.getLogin()));
        return String.format(Message.MSG_DELETE, user.getLogin());
    }

    /**
     * Find all in the container.
     * @return container for user.
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.store.values());
    }

    /**
     * Find user by id.
     * @param id user id.
     * @return true if user exist.
     */
    @Override
    public User findById(String id) {
        User user = this.store.get(id);
        if (user != null) {
            return user;
        }
        throw new UserIdException(String.format(Message.MSG_ID_NOT_EXIST, id));
    }

    /**
     * Check login in storage.
     *
     * @param login user login
     * @return true if login is unique.
     */
    private boolean checkLogin(String login) {
        return this.store.values().stream().noneMatch(
                user -> user.getLogin().equals(login));
    }

    /**
     * Check login in storage.
     *
     * @param login user login
     * @param id user id
     * @return true if login is unique.
     */
    private boolean checkLogin(String login, String id) {
        return this.store.values().stream().filter(user -> !user.getId().equals(id))
                .noneMatch(user -> user.getLogin().equals(login));
    }

    /**
     * Is Credentials.
     *
     * @param login user login
     * @param password user password
     * @return Optional for get result in Signings Servlet
     */
    public Optional<User> isCredentials(String login, String password) {
        return this.store.values().stream().filter(user -> user.getLogin().equals(login)
                && user.getPassword().equals(password)).findFirst();
    }
}
