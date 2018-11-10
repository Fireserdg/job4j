package ru.job4j.crud;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

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
    private final long createDate;

    /**
     * Constructor with id.
     * @param id user id.
     * @param name user name.
     * @param login login.
     * @param email email.
     * @param createDate create.
     */
    public User(final String id, final String name, final String login,
                final String email, final long createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    /**
     * Get user name.
     * @return user name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get user login.
     * @return user login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Get user email.
     * @return user email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get create date.
     * @return create date.
     */
    public long getCreateDate() {
        return createDate;
    }

    /**
     * Get user id.
     *
     * @return id
     */
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, name=%s, create=%s]",
                this.id, this.name, LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(createDate),
                        TimeZone.getDefault().toZoneId()).format(
                        DateTimeFormatter.ofPattern("yyyy-MMM-dd, HH:mm").withLocale(new Locale("en"))
                ));
    }
}