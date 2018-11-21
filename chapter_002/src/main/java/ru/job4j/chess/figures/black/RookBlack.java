package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.*;

/**
 * Фигура - Ладья черная.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class RookBlack extends Figure {

    /**
     * Конструктор.
     *
     * @param position Позиция на шахматной доске.
     */
    public RookBlack(final Cell position) {
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
        int moveX = Integer.compare(source.x, dest.x);
        int moveY = Integer.compare(source.y, dest.y);
        if (!(moveX == 0 || moveY == 0)) {
            throw new ImpossibleMoveException("Фигура так пойти не может");
        } else {
            steps = moveX == 0 ? new Cell[Math.abs(source.y - dest.y)] : new Cell[Math.abs(source.x - dest.x)];
        }

        for (int i = 0; i < steps.length; i++) {
            if (moveX == 0) {
                steps[i] = Cell.values()[(8 * source.x + source.y) - moveY];
                moveY = moveY > 0 ? moveY + 1 : moveY - 1;
            } else  {
                steps[i] = Cell.values()[(8 * source.x + source.y) - moveX * 8];
                moveX = moveX > 0 ? moveX + 1 : moveX - 1;
            }
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
        return new RookBlack(dest);
    }
}
