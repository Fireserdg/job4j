package ru.job4j.bomberman;

/**
 * Cell.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 05.09.2018.
 */
public class Cell {

    /**
     * Contains x;
     *
     */
    private final int x;

    /**
     * Contains y.
     *
     */
    private final int y;

    /**
     * Constructor.
     *
     * @param x coordinate x.
     * @param y coordinate y.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("Cell[x=%s, y=%s]", x, y);
    }
}
