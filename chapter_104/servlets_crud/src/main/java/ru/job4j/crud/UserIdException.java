package ru.job4j.crud;

/**
 * Exception if id does not exist in Storage.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 04.12.18
 */
public class UserIdException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param msg message about error
     */
    public UserIdException(String msg) {
        super(msg);
    }
}
