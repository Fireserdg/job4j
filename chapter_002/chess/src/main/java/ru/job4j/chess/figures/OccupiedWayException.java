package ru.job4j.chess.figures;

/**
 * Исключение если клетка занята.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class OccupiedWayException extends RuntimeException {

    /**
     * Конструктор.
     * @param msg message about exception.
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }
}
