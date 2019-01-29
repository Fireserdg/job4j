package ru.job4j.crud;

/**
 * User login exception.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 14.11.18
 */
public class UserLoginException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param msg message about error
     */
    public UserLoginException(String msg) {
        super(msg);
    }
}