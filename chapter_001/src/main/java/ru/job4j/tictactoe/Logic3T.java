package ru.job4j.tictactoe;

/**
 * Класс отвечает за проверку логики.
 */
public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
            return (table[0][0].hasMarkX() && table[1][1].hasMarkX() && table[2][2].hasMarkX())
                    || (table[0][2].hasMarkX() && table[1][1].hasMarkX() && table[2][0].hasMarkX())
                    || (table[0][0].hasMarkX() && table[0][1].hasMarkX() && table[0][2].hasMarkX())
                    || (table[0][0].hasMarkX() && table[1][0].hasMarkX() && table[2][0].hasMarkX())
                    || (table[0][1].hasMarkX() && table[1][1].hasMarkX() && table[2][1].hasMarkX())
                    || (table[0][2].hasMarkX() && table[1][2].hasMarkX() && table[2][2].hasMarkX())
                    || (table[1][0].hasMarkX() && table[1][1].hasMarkX() && table[1][2].hasMarkX())
                    || (table[2][0].hasMarkX() && table[2][1].hasMarkX() && table[2][2].hasMarkX());
    }

    public boolean isWinnerO() {
        return (table[0][0].hasMarkO() && table[1][1].hasMarkO() && table[2][2].hasMarkO())
                || (table[0][2].hasMarkO() && table[1][1].hasMarkO() && table[2][0].hasMarkO())
                || (table[0][0].hasMarkO() && table[0][1].hasMarkO() && table[0][2].hasMarkO())
                || (table[0][0].hasMarkO() && table[1][0].hasMarkO() && table[2][0].hasMarkO())
                || (table[0][1].hasMarkO() && table[1][1].hasMarkO() && table[2][1].hasMarkO())
                || (table[0][2].hasMarkO() && table[1][2].hasMarkO() && table[2][2].hasMarkO())
                || (table[1][0].hasMarkO() && table[1][1].hasMarkO() && table[1][2].hasMarkO())
                || (table[2][0].hasMarkO() && table[2][1].hasMarkO() && table[2][2].hasMarkO());
    }

    public boolean hasGap() {
        boolean result = true;
        if (table[0][0].hasMarkX() && table[0][1].hasMarkO() && table[0][2].hasMarkO()
                && table[1][0].hasMarkX() && table[1][1].hasMarkO() && table[1][2].hasMarkX()
                && table[2][0].hasMarkO() && table[2][1].hasMarkX() && table[2][2].hasMarkO()) {
            result = false;
        } else if (table[0][0].hasMarkX() && table[0][1].hasMarkX() && table[0][2].hasMarkO()
                && table[1][0].hasMarkO() && table[1][1].hasMarkO() && table[1][2].hasMarkX()
                && table[2][0].hasMarkX() && table[2][1].hasMarkO() && table[2][2].hasMarkX()) {
            result = false;
        } else if (table[0][0].hasMarkX() && table[0][1].hasMarkX() && table[0][2].hasMarkO()
                && table[1][0].hasMarkO() && table[1][1].hasMarkO() && table[1][2].hasMarkX()
                && table[2][0].hasMarkX() && table[2][1].hasMarkX() && table[2][2].hasMarkO()) {
            result = false;
        } else if (table[0][0].hasMarkX() && table[0][1].hasMarkO() && table[0][2].hasMarkX()
                && table[1][0].hasMarkO() && table[1][1].hasMarkO() && table[1][2].hasMarkX()
                && table[2][0].hasMarkX() && table[2][1].hasMarkX() && table[2][2].hasMarkO()) {
            result = false;
        } else if (table[0][0].hasMarkO() && table[0][1].hasMarkX() && table[0][2].hasMarkX()
                && table[1][0].hasMarkX() && table[1][1].hasMarkX() && table[1][2].hasMarkO()
                && table[2][0].hasMarkO() && table[2][1].hasMarkO() && table[2][2].hasMarkX()) {
            result = false;
        } else if (table[0][0].hasMarkX() && table[0][1].hasMarkO() && table[0][2].hasMarkX()
                && table[1][0].hasMarkO() && table[1][1].hasMarkX() && table[1][2].hasMarkX()
                && table[2][0].hasMarkO() && table[2][1].hasMarkX() && table[2][2].hasMarkO()) {
            result = false;
        } else if (table[0][0].hasMarkO() && table[0][1].hasMarkO() && table[0][2].hasMarkX()
                && table[1][0].hasMarkX() && table[1][1].hasMarkX() && table[1][2].hasMarkO()
                && table[2][0].hasMarkO() && table[2][1].hasMarkX() && table[2][2].hasMarkX()) {
            result = false;
        } else if (table[0][0].hasMarkO() && table[0][1].hasMarkX() && table[0][2].hasMarkO()
                && table[1][0].hasMarkX() && table[1][1].hasMarkO() && table[1][2].hasMarkX()
                && table[2][0].hasMarkX() && table[2][1].hasMarkO() && table[2][2].hasMarkX()) {
            result = false;
        } else if (table[0][0].hasMarkX() && table[0][1].hasMarkO() && table[0][2].hasMarkX()
                && table[1][0].hasMarkX() && table[1][1].hasMarkX() && table[1][2].hasMarkO()
                && table[2][0].hasMarkO() && table[2][1].hasMarkX() && table[2][2].hasMarkO()) {
            result = false;
        } else if (table[0][0].hasMarkO() && table[0][1].hasMarkX() && table[0][2].hasMarkX()
                && table[1][0].hasMarkX() && table[1][1].hasMarkO() && table[1][2].hasMarkO()
                && table[2][0].hasMarkO() && table[2][1].hasMarkX() && table[2][2].hasMarkX()) {
            result = false;
        } else if (table[0][0].hasMarkX() && table[0][1].hasMarkX() && table[0][2].hasMarkO()
                && table[1][0].hasMarkO() && table[1][1].hasMarkX() && table[1][2].hasMarkX()
                && table[2][0].hasMarkX() && table[2][1].hasMarkO() && table[2][2].hasMarkO()) {
            result = false;
        } else if (table[0][0].hasMarkX() && table[0][1].hasMarkO() && table[0][2].hasMarkX()
                && table[1][0].hasMarkX() && table[1][1].hasMarkO() && table[1][2].hasMarkO()
                && table[2][0].hasMarkO() && table[2][1].hasMarkX() && table[2][2].hasMarkX()) {
            result = false;
        } else if (table[0][0].hasMarkX() && table[0][1].hasMarkO() && table[0][2].hasMarkX()
                && table[1][0].hasMarkX() && table[1][1].hasMarkO() && table[1][2].hasMarkX()
                && table[2][0].hasMarkO() && table[2][1].hasMarkX() && table[2][2].hasMarkO()) {
            result = false;
        } else if (table[0][0].hasMarkX() && table[0][1].hasMarkO() && table[0][2].hasMarkX()
                && table[1][0].hasMarkX() && table[1][1].hasMarkO() && table[1][2].hasMarkX()
                && table[2][0].hasMarkO() && table[2][1].hasMarkX() && table[2][2].hasMarkO()) {
            result = false;
        } else if (table[0][0].hasMarkO() && table[0][1].hasMarkX() && table[0][2].hasMarkX()
                && table[1][0].hasMarkX() && table[1][1].hasMarkO() && table[1][2].hasMarkO()
                && table[2][0].hasMarkX() && table[2][1].hasMarkO() && table[2][2].hasMarkX()) {
            result = false;
        } else if (table[0][0].hasMarkO() && table[0][1].hasMarkX() && table[0][2].hasMarkO()
                && table[1][0].hasMarkO() && table[1][1].hasMarkX() && table[1][2].hasMarkX()
                && table[2][0].hasMarkX() && table[2][1].hasMarkO() && table[2][2].hasMarkX()) {
            result = false;
        } else if (table[0][0].hasMarkO() && table[0][1].hasMarkX() && table[0][2].hasMarkO()
                && table[1][0].hasMarkO() && table[1][1].hasMarkX() && table[1][2].hasMarkO()
                && table[2][0].hasMarkX() && table[2][1].hasMarkO() && table[2][2].hasMarkX()) {
            result = false;
        } else if (table[0][0].hasMarkO() && table[0][1].hasMarkX() && table[0][2].hasMarkO()
                && table[1][0].hasMarkO() && table[1][1].hasMarkX() && table[1][2].hasMarkX()
                && table[2][0].hasMarkX() && table[2][1].hasMarkO() && table[2][2].hasMarkO()) {
            result = false;
        }
        return result;
    }
}