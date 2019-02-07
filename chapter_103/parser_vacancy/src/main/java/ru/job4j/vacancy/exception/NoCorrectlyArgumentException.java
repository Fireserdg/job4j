package ru.job4j.vacancy.exception;

/**
 *  Exception for wrong argument.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */
public class NoCorrectlyArgumentException extends RuntimeException {

    /**
     * Constructor
     *
     * @param message message about exception
     */
    public NoCorrectlyArgumentException(String message) {
        super(message);
    }
}
