package ru.job4j.chess.figures.white;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * Фигура - Ладья белая.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class RookWhite extends Figure {

    public RookWhite(final Cell position) {
        super(position);
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        return new Cell[] { dest };
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}