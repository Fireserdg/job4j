package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.ImpossibleMoveException;

/**
 * Фигура - пешка черная.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class PawnBlack extends Figure {

    /**
     * Конструктор.
     *
     * @param position Позиция на шахматной доске.
     */
    public PawnBlack(final Cell position) {
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
        Cell[] steps;
        if (source.y == dest.y + 1 && source.x == dest.x) {
            steps = new Cell[] {dest};
        } else {
            throw new ImpossibleMoveException("Фигура так пойти не может");
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
        return new PawnBlack(dest);
    }
}
