package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.*;

/**
 * Фигура - Король черный.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class KingBlack extends Figure {

    /**
     * Конструктор.
     *
     * @param position Позиция на шахматной доске.
     */
    public KingBlack(final Cell position) {
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
        int moveX = Math.abs(source.x - dest.x);
        int moveY = Math.abs(source.y - dest.y);
        if (moveX > 1 || moveY > 1) {
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
        return new KingBlack(dest);
    }
}
