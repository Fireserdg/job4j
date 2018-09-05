package ru.job4j.pool;

/**
 * User.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 05.09.2018.
 */
public class User {

    /**
     * Username.
     *
     */
    private final String username;

    /**
     * E-mail.
     *
     */
    private final String email;

    /**
     * Constructor.
     *
     * @param username Username.
     * @param email e-mail.
     */
    public User(final String username, final String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
