package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.ImpossibleMoveException;

/**
 * Фигура - Ферзь черный.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class QeenBlack extends Figure {

    /**
     * Field to call a method of the figure.
     */
    RookBlack rook = new RookBlack(position);

    /**
     * Field to call a method of the figure
     */
    BishopBlack bishop = new BishopBlack(position);

    /**
     * Конструктор.
     *
     * @param position Позиция на шахматной доске.
     */
    public QeenBlack(final Cell position) {
        super(position);
    }

    /**
     * Позиция фигуры.
     *
     * @return Позиция.
     */
    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Проверка возможности хода.
     *
     * @param source Клетка на которой находится фигура.
     * @param dest Клетка куда должна переместиться фигура.
     * @return Массив клеток, которые проходит фигура.
     * @throws ImpossibleMoveException Если фигура не может передвинуться.
     */
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        int deltaX = Integer.compare(source.x, dest.x);
        int deltaY = Integer.compare(source.y, dest.y);
        Cell[] steps;
        if (deltaX == 0 || deltaY == 0) {
            steps = rook.way(source, dest);
        } else if (bishop.isDiagonal(source, dest)) {
            steps = bishop.way(source, dest);
        } else {
            throw new ImpossibleMoveException();
        }
        return steps;
    }

    /**
     * Перемещение фигуры на заданную позицию.
     *
     * @param dest Новая клетка для фигуры.
     * @return Фигура с новой координатой.
     */
    @Override
    public Figure copy(Cell dest) {
        return new QeenBlack(dest);
    }
}
