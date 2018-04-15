package ru.job4j.loop;
/**
 * Построение шахматной доски в псевдографике.
 *  *@author Sergey Filippov (serdg1984@yandex.ru)
 *  *@version $Id$
 *  *@since 0.1
 */
public class Board {
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int value = 0; value < height; value++) {
            for (int out = 0; out < width; out++) {
                if ((value + out) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}
