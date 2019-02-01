package ru.job4j.model;

import java.util.Objects;

/**
 * User account
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public class Accounts {

    /**
     * Account id.
     */
    private int id;

    /**
     * Username.
     */
    private String username;

    /**
     * User phone.
     */
    private String phone;

    public Accounts(int id, String username, String phone) {
        this.username = username;
        this.phone = phone;
        this.id = id;
    }

    public Accounts() {
    }

    /**
     * Get account id.
     * @return account id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get username
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get user phone.
     * @return user phone.
     */
    public String getPhone() {
        return phone;
    }

//    /**
//     * Set account id.
//     * @param id account id.
//     */
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    /**
//     * Set username.
//     * @param username username.
//     */
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    /**
//     * Set user phone.
//     * @param phone user phone.
//     */
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }

    /**
     * Equals between objects.
     * @param o other object.
     * @return true if the same object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accounts accounts = (Accounts) o;
        return id == accounts.id
                && Objects.equals(username, accounts.username)
                && Objects.equals(phone, accounts.phone);
    }

    /**
     * Account hashcode.
     * @return hashcode account.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, phone);
    }

    /**
     * String representation of an object.
     * @return representation of an object.
     */
    @Override
    public String toString() {
        return String.format("Accounts{id=%s, username=%s, phone=%s}",
                this.id, this.username, this.phone);
    }
}
