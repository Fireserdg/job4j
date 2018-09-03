package ru.job4j.cash;

/**
 * Optimistic exception.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 03.09.2018.
 */
public class OptimisticException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param msg massage for user.
     */
    public OptimisticException(String msg) {
        super(msg);
    }
}
