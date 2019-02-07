package ru.job4j.chess.figures.white;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.black.QeenBlack;

/**
 * Фигура - Ферзь белый.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class QeenWhite extends QeenBlack {

    /**
     * Конструктор.
     *
     * @param position Позиция на шахматной доске.
     */
    public QeenWhite(final Cell position) {
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
        return new QeenWhite(dest);
    }
}