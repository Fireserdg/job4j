package ru.job4j.beans.entity;

import java.util.Objects;

/**
 * Entity User
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-05-02
 */
public class User {

    /**
     * User id
     */
    private Long id;

    /**
     * User name
     *
     */
    private String name;

    /**
     * Constructor
     *
     * @param name user name
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Get id
     *
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Set id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Equals between other user
     *
     * @param o other user
     * @return equals result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id)
                && Objects.equals(name, user.name);
    }

    /**
     * Hashcode object
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * user toString
     *
     * @return string about user
     */
    @Override
    public String toString() {
        return String.format("User{id=%s, name=%s}", this.id, this.name);
    }
}
