package ru.job4j.tracker;
/**
 * Класс принимающий сообщения об ошибках.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 14.07.2018.
 */
public class MenuOutException extends RuntimeException {
    public MenuOutException(String msg) {
        super(msg);
    }
}
