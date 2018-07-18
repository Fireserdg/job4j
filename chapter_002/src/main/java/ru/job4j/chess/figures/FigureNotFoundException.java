package ru.job4j.chess.figures;

/**
 * Исключение если фигура отсутствует.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class FigureNotFoundException extends Exception {

    /**
     * Конструктор.
     *
     * @param msg Сообщение об ошибке.
     */
    public FigureNotFoundException (String msg) {
        super(msg);
    }
}
