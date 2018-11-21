package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.*;

/**
 * Фигура - Слон черный.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class BishopBlack extends Figure {

    /**
     * Конструктор.
     *
     * @param position Позиция на шахматной доске.
     */
    public BishopBlack(final Cell position) {
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
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("Фигура так пойти не может");
        }
        Cell[] steps = new Cell[Math.abs(source.x - dest.x)];
        int deltaX = Integer.compare(source.x, dest.x);
        int deltaY = Integer.compare(source.y, dest.y);
        for (int index = 0; index < steps.length; index++) {
            steps[index] = Cell.values()[(8 * (source.x - deltaX)) + (source.y - deltaY)];
            deltaX = deltaX < 0 ? deltaX - 1 : deltaX + 1;
            deltaY = deltaY < 0 ? deltaY - 1 : deltaY + 1;
        }
        return steps;
    }

    /**
     * Проверка движения слона по диагонали.
     *
     * @param source Клетка начала движения.
     * @param dest Клетка конца движения.
     * @return Возможность перемещения по диагонали.
     */
    public boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y);
    }

    /**
     * Перемещение фигуры на заданную позицию.
     *
     * @param dest Новая клетка для фигуры.
     * @return Фигура с новой координатой.
     */
    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
