package ru.job4j.model;

/**
 * Accounts
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public class Accounts {

    private int id;

    private String username;

    private String phone;

    public Accounts(int id, String username, String phone) {
        this.username = username;
        this.phone = phone;
        this.id = id;
    }

    public Accounts() {
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

}
