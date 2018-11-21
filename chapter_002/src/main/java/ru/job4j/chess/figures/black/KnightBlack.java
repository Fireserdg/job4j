package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.*;

/**
 * Фигура - Конь черный.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class KnightBlack extends Figure {

    /**
     * Конструктор.
     *
     * @param position Позиция на шахматной доске.
     */
    public KnightBlack(final Cell position) {
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
        int canMove = deltaX * (source.x - dest.x) + deltaY * (source.y - dest.y);
        if (!(canMove == 3) || deltaX == 0 || deltaY == 0) {
            throw new ImpossibleMoveException("Фигура так пойти не может");
        } else {
            return new Cell[] {dest};
        }
    }

    /**
     * Перемещение фигуры на заданную позицию.
     *
     * @param dest Новая клетка для фигуры.
     * @return Фигура с новой координатой.
     */
    @Override
    public Figure copy(Cell dest) {
        return new KnightBlack(dest);
    }
}
