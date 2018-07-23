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
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                int count = 0;
                if (table[i][j].hasMarkX()) {
                    if (table[j][count].hasMarkX() && table[j][count + 1].hasMarkX()
                            && table[j][count + 2].hasMarkX()) {
                        result = true;
                        break;
                    } else if (table[count][j].hasMarkX() && table[count + 1][j].hasMarkX()
                            && table[count + 2][j].hasMarkX()) {
                        result = true;
                        break;
                    } else if (table[count][count].hasMarkX() && table[count + 1][count + 1].hasMarkX()
                            && table[count + 2][count + 2].hasMarkX()) {
                        result = true;
                        break;
                    } else if (table[count + 2][count].hasMarkX() && table[count + 1][count + 1].hasMarkX()
                            && table[count][count + 2].hasMarkX()) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public boolean isWinnerO() {
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                int count = 0;
                if (table[i][j].hasMarkO()) {
                    if (table[j][count].hasMarkO() && table[j][count + 1].hasMarkO()
                            && table[j][count + 2].hasMarkO()) {
                        result = true;
                        break;
                    } else if (table[count][j].hasMarkO() && table[count + 1][j].hasMarkO()
                            && table[count + 2][j].hasMarkO()) {
                        result = true;
                        break;
                    } else if (table[count][count].hasMarkO() && table[count + 1][count + 1].hasMarkO()
                            && table[count + 2][count + 2].hasMarkO()) {
                        result = true;
                        break;
                    } else if (table[count + 2][count].hasMarkO() && table[count + 1][count + 1].hasMarkO()
                            && table[count][count + 2].hasMarkO()) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public boolean hasGap() {
        boolean result = true;
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j].hasMarkO() || table[i][j].hasMarkX()) {
                    count++;
                    if (count == table.length * table[0].length) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }
}