package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.ImpossibleMoveException;

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
            throw new ImpossibleMoveException("Cлон, не может так ходить");
        }
        int sizeSteps = Math.abs(source.x - dest.x);
        Cell[] steps = new Cell[sizeSteps];
        int deltaX = source.x - dest.x;
        int deltaY = source.y - dest.y;
        if (deltaX > 0 && deltaY > 0) {
            deltaX = 1;
            deltaY = 1;
        } else if (deltaX < 0 && deltaY < 0) {
            deltaX = - 1;
            deltaY = - 1;
        } else if (deltaX < 0 && deltaY > 0) {
            deltaX = - 1;
            deltaY = 1;
        } else {
            deltaX = 1;
            deltaY = - 1;
        }

        for (int index = 0; index < steps.length ; index++) {
            steps[index] = Cell.values()[(8 * (source.x - deltaX)) + (source.y - deltaY)];
            if(deltaX < 0){
                deltaX--;
            } else {
                deltaX++;
            }
            if(deltaY < 0) {
                deltaY--;
            } else {
                deltaY++;
            }
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
        boolean result = false;
        if (Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y)) {
            result = true;
        }
        return result;
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
