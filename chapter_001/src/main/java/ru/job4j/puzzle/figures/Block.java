package ru.job4j.puzzle.figures;

/**
 * class Block.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.10.2018.
 */
public class Block implements Figure {
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
    public Block(final Cell position) {
        this.position = position;
    }

    /**
     * Get position
     *
     * @return current position
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
        return new Cell[0];
    }

    /**
     * Get Figure
     *
     * @param dest cell dest
     * @return figure
     */
    @Override
    public Figure copy(Cell dest) {
        throw new IllegalStateException("Block could not move.");
    }

    /**
     * Movable
     *
     * @return false
     */
    @Override
    public boolean movable() {
        return false;
    }
}