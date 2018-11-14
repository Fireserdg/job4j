package ru.job4j.crud;

/**
 * User not found exception.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 14.11.18
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param msg message about error
     */
    public UserNotFoundException(String msg) {
        super(msg);
    }
}