package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.models.Role;
import ru.job4j.crud.models.User;
import ru.job4j.crud.store.DbStore;
import ru.job4j.crud.store.Store;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Validate Service
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 03.11.18
 */
public class ValidateService implements Validate {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ValidateService.class);

    /**
     * Contains class instance.
     */
    private static final Validate VALIDATE = new ValidateService();

    /**
     * Contains storage.
     */
    private static final Store<User> STORE = DbStore.getInstance();

    /**
     * Constructor.
     */
    private ValidateService() {
    }

    /**
     * Get instance.
     * @return instance of ValidateService.
     */
    public static Validate getInstance() {
        return VALIDATE;
    }

    /**
     * Add user.
     * @param params values of user.
     * @return msg about add user.
     */
    public String add(final String[] params) {
        if (checkLogin(params[1])) {
            User result = STORE.add(new User(params[0], params[1],
                    params[2], params[3], System.currentTimeMillis(),
                    Role.valueOf(params[4]), params[5], params[6]));
            if (result != null) {
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
    public String update(final String[] params) {
        User oldUser = this.findById(params[0]);
        if (checkLogin(params[2], oldUser.getId())) {
            STORE.update(new User(oldUser.getId(),
                    () -> params[1].equals("") ? oldUser.getName() : params[1],
                    () -> params[2].equals("") ? oldUser.getLogin() : params[2],
                    () -> params[3].equals("") ? oldUser.getPassword() : params[3],
                    () -> params[4].equals("") ? oldUser.getEmail() : params[4],
                    oldUser.getCreate(), Role.valueOf(params[5]), params[6], params[7]));
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
    public String delete(final String id) {
        User user = this.findById(id);
        STORE.delete(user.getId());
        LOG.info(String.format(Message.MSG_DELETE, user.getLogin()));
        return String.format(Message.MSG_DELETE, user.getLogin());
    }

    /**
     * Find all in the container.
     * @return container for user.
     */
    public List<User> findAll() {
        return STORE.findAll();
    }

    /**
     * Find user by id.
     * @param id user id.
     * @return true if user exist.
     */
    public User findById(String id) {
        User user = STORE.findById(id);
        if (user != null) {
            return user;
        }
        throw new UserIdException(String.format(Message.MSG_ID_NOT_EXIST, id));
    }

    /**
     * Get country
     * @return country
     */
    @Override
    public Map<String, String> getCountry() {
        return STORE.getCountry();
    }

    /**
     * Get city by id
     *
     * @param id country id
     * @return list of city
     */
    @Override
    public List<String> getCity(String id) {
        return STORE.getCity(id);
    }

    /**
     * Check login in storage.
     *
     * @param login user login
     * @return true if login is unique.
     */
    private boolean checkLogin(String login) {
        return STORE.findAll().stream().noneMatch(
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
        return STORE.findAll().stream().filter(user -> !user.getId().equals(id))
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
        return STORE.findAll().stream().filter(user -> user.getLogin().equals(login)
                && user.getPassword().equals(password)).findFirst();
    }
}