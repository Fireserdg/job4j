package ru.job4j.puzzle.figures;

import java.util.Objects;

/**
 * class Cell.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.10.2018.
 */
public class Cell {

    /**
     * Contains coordinate x
     *
     */
    public final int x;

    /**
     * Contains coordinate y
     *
     */
    public final int y;

    /**
     * Constructor
     *
     * @param x coordinate x
     * @param y coordinate y
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return x == cell.x
                && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}