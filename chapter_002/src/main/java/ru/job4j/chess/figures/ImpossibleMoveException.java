package ru.job4j.chess.figures;

/**
 * Исключение если траектория фигуры не верная.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class ImpossibleMoveException extends RuntimeException {

    /**
     * Конструктор.
     * @param msg message about exception.
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}
