package ru.job4j.storage;

/**
 * User.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 25.08.2018.
 */
public class User {

    /**
     * Contains id.
     *
     */
    private final int id;

    /**
     * Contains amount.
     *
     */
    private int amount;

    /**
     * Constructor User.
     *
     * @param id user id.
     * @param amount user amount.
     */
    public User(final int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}