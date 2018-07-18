package ru.job4j.chess.figures;

/**
 * Исключение если клетка занята.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class OccupiedWayException extends Exception {

    /**
     * Конструктор.
     *
     * @param msg Сообщение об ошибке.
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }
}
