package ru.job4j.storage;

/**
 *  Create database exception.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 04.10.2018.
 */
public class CreateDataBaseException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param msg message about exception.
     */
    public CreateDataBaseException(String msg) {
        super(msg);
    }
}