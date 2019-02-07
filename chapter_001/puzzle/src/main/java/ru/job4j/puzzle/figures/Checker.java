package ru.job4j.puzzle.figures;

/**
 * class Checker.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.10.2018.
 */
public class Checker implements Figure {

    /**
     * Contains position
     *
     */
    private final Cell position;

    /**
     * Constructor
     *
     * @param position position
     */
    public Checker(final Cell position) {
        this.position = position;
    }

    /**
     * Get position
     *
     * @return Cell
     */
    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Get way
     *
     * @param source current cell
     * @param dest dest cell
     * @return way
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if ((Math.abs(source.x - dest.x) + Math.abs(source.y - dest.y)) == 1) {
            steps = new Cell[]{dest};
        }
        return steps;
    }

    /**
     * Get Figure
     *
     * @param dest cell dest
     * @return figure
     */
    @Override
    public Figure copy(Cell dest) {
        return new Checker(dest);
    }
}