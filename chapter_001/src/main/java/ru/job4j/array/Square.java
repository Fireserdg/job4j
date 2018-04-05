package ru.job4j.array;
/**
 *Программа возведения числа в степень.
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 0.1
 */
public class Square {
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 0; i < bound; i++) {
          rst [i] = (int) Math.pow(i + 1, 2);
        }
        return rst;
    }
}
