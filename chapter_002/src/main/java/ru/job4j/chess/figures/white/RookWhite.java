package ru.job4j.chess.figures.white;

import ru.job4j.chess.figures.*;
import ru.job4j.chess.figures.black.RookBlack;

/**
 * Фигура - Ладья белая.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class RookWhite extends Figure {

    /**
     * Field to call a method of the figure.
     */
    private RookBlack rook = new RookBlack(position);

    /**
     * Конструктор.
     *
     * @param position Позиция на шахматной доске.
     */
    public RookWhite(final Cell position) {
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
        return rook.way(source, dest);
    }

    /**
     * Перемещение фигуры на заданную позицию.
     *
     * @param dest Новая клетка для фигуры.
     * @return Фигура с новой координатой.
     */
    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}
