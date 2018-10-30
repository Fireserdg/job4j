package ru.job4j.puzzle.figures;

/**
 * interface Figure.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.10.2018.
 */
public interface Figure {

    /**
     * Movable
     *
     * @return true
     */
    default boolean movable() {
        return true;
    }

    /**
     * Get position
     *
     * @return current position
     */
    Cell position();

    /**
     * Get way
     *
     * @param source current cell
     * @param dest dest cell
     * @return way
     */
    Cell[] way(Cell source, Cell dest);

    /**
     * Icon for figure
     *
     * @return icon.
     */
    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    /**
     * Get Figure
     *
     * @param dest cell dest
     * @return figure
     */
    Figure copy(Cell dest);
}