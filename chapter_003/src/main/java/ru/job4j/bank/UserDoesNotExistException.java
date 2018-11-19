package ru.job4j.bank;


/**
 * Exception if user does not exist
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 19.11.18
 */
public class UserDoesNotExistException extends RuntimeException {

    /**
     * Constructor
     *
     * @param msg message about exception.
     */
    public UserDoesNotExistException(String msg) {
        super(msg);
    }
}
