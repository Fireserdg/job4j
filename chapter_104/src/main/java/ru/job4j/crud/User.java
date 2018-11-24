package ru.job4j.crud;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.function.Supplier;

/**
 * User model
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 02.11.18
 */
public class User {

    /**
     * User id.
     *
     */
    private String id;
    /**
     * User name.
     */
    private final String name;

    /**
     * User login.
     */
    private final String login;

    /**
     * User name.
     */
    private final String email;

    /**
     * Create date.
     */
    private final long create;

    /**
     * Constructor for create user.
     * @param name user name.
     * @param login login.
     * @param email email.
     * @param create create.
     */
    public User(final String name, final String login,
                final String email, final long create) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.create = create;
    }

    /**
     * Constructor for create user and id.
     * @param id user id.
     * @param name user name.
     * @param login login.
     * @param email email.
     * @param create create.
     */
    public User(final String id, final String name, final String login,
                final String email, final long create) {
        this(name, login, email, create);
        this.id = id;
    }

    /**
     * Constructor for create user with Supplier.
     * @param id user id.
     * @param name user name.
     * @param login login.
     * @param email email.
     * @param create create.
     */
    public User(final String id, final Supplier<String> name,
                final Supplier<String> login,
                final Supplier<String> email,
                final long create) {
        this(id, name.get(), login.get(), email.get(), create);
    }


    /**
     * Get user name.
     * @return user name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get user login.
     * @return user login.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Get user email.
     * @return user email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Get create date.
     * @return create date.
     */
    public long getCreate() {
        return this.create;
    }

    /**
     * Get user id.
     *
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set id for user
     *
     * @param id id
     */
    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, name=%s, login=%s, email=%s, create=%s]",
                this.id, this.name, this.login, this.email,
                        LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(create),
                        TimeZone.getDefault().toZoneId()).format(
                        DateTimeFormatter.ofPattern("yyyy-MMM-dd, HH:mm").withLocale(new Locale("en"))
                ));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return create == user.create
                && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, create);
    }
}