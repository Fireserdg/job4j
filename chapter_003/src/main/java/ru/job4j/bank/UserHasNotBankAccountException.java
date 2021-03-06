package ru.job4j.bank;

/**
 * Bank operations.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 02.08.2018.
 */
public class UserHasNotBankAccountException extends RuntimeException {

    /**
     * Constructor
     *
     * @param msg message about exception.
     */
    public UserHasNotBankAccountException(String msg) {
        super(msg);
    }

}
