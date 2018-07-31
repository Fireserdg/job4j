package ru.job4j.chess.figures.white;

import ru.job4j.chess.figures.*;
import ru.job4j.chess.figures.black.KnightBlack;

/**
 * Фигура - Конь белый.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class KnightWhite extends Figure {

    /**
     * Field to call a method of the figure.
     */
    private KnightBlack knight = new KnightBlack(position);

    /**
     * Конструктор.
     *
     * @param position Позиция на шахматной доске.
     */
    public KnightWhite(final Cell position) {
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
        return knight.way(source, dest);
    }

    /**
     * Перемещение фигуры на заданную позицию.
     *
     * @param dest Новая клетка для фигуры.
     * @return Фигура с новой координатой.
     */
    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}
