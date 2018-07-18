package ru.job4j.chess.figures;

/**
 * Абстрактный класс для реализации шахматных фигур.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public abstract class Figure {
    /**
     * Объект Cell.
     */
    public final Cell position;

    /**
     * Конструктор.
     *
     * @param position Клетка.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Проверка возможности хода фигуры.
     *
     * @param source Клетка на которой находится фигура.
     * @param dest Клетка куда должна переместиться фигура.
     * @return Массив клеток, которые проходит фигура.
     * @throws ImpossibleMoveException Если фигура не может передвинуться.
     */
    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    public abstract Cell position();

    /**
     * Изображение фигуры.
     *
     * @return Изображение.
     */
    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    /**
     * Перемещение фигуры на заданную позицию.
     *
     * @param dest Новая клетка для фигуры.
     * @return Фигура с новой координатой.
     */
    public abstract Figure copy(Cell dest);

}
