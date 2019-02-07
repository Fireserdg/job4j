package ru.job4j.chess.figures.white;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.black.BishopBlack;

/**
 * Фигура - Слон белый.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class BishopWhite extends BishopBlack {

    /**
     * Конструктор.
     *
     * @param position Позиция на шахматной доске.
     */
    public BishopWhite(final Cell position) {
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
        return new BishopWhite(dest);
    }
}
