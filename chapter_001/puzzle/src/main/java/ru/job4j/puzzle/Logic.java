package ru.job4j.puzzle;

import ru.job4j.puzzle.figures.Cell;
import ru.job4j.puzzle.figures.Figure;

/**
 * class Logic.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.10.2018.
 */
public class Logic {

    /**
     * Contains size
     *
     */
    private final int size;

    /**
     * Contains Figure arrays
     *
     */
    private final Figure[] figures;

    /**
     * Contains index
     *
     */
    private int index = 0;

    /**
     * Constructor
     *
     * @param size size
     */
    public Logic(int size) {
        this.size = size;
        this.figures = new Figure[size * size];
    }

    /**
     * Add figure
     *
     * @param figure Figure
     */
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Move
     *
     * @param source current Cell
     * @param dest dest Cell
     * @return check result
     */
    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (this.isFree(steps)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }

    /**
     * Check free
     *
     * @param cells Cell
     * @return check result
     */
    public boolean isFree(Cell ... cells) {
        boolean result = cells.length > 0;
        for (Cell cell : cells) {
            if (this.findBy(cell) != -1) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Clean
     *
     */
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * Find by Cell
     *
     * @param cell Cell
     * @return value
     */
    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * Check isWin
     *
     * @return check result
     */
    public boolean isWin() {
        boolean result = false;
        int[][] table = this.convert();
        int index = -1, value = 0;
        for (int[] row : table) {
            int sum = 0;
            for (int cell = 0; cell < row.length; cell++) {
                if (row[cell] == 1 && index == -1) {
                    index = cell;
                } else if (row[cell] == 1 && index == cell) {
                    value++;
                }
                sum += row[cell];
            }
            if (sum == 5 || value == 4) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Convert table
     *
     * @return table
     */
    public int[][] convert() {
        int[][] table = new int[this.size][this.size];
        for (int row = 0; row != table.length; row++) {
            for (int cell = 0; cell != table.length; cell++) {
                int position = this.findBy(new Cell(row, cell));
                if (position != -1 && this.figures[position].movable()) {
                    table[row][cell] = 1;
                }
            }
        }
        return table;
    }
}