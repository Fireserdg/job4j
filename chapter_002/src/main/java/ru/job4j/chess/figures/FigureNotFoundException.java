package ru.job4j.chess.figures;

/**
 * Исключение если фигура отсутствует.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class FigureNotFoundException extends RuntimeException {

    /**
     * Конструктор.
     * @param msg message about exception.
     */
    public FigureNotFoundException(String msg) {
        super(msg);
    }
}
