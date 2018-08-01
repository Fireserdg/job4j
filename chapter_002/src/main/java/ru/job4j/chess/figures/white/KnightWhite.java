package ru.job4j.chess.figures.white;

import ru.job4j.chess.figures.*;
import ru.job4j.chess.figures.black.KnightBlack;

/**
 * Фигура - Конь белый.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class KnightWhite extends KnightBlack {

    /**
     * Конструктор.
     *
     * @param position Позиция на шахматной доске.
     */
    public KnightWhite(final Cell position) {
        super(position);
    }

    /**
     * Перемещение фигуры на заданную позицию.
     *
     * @param dest Новая клетка для фигуры.
     * @return Фигура с новой координатой.
     */
    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}
