package ru.job4j.array;
/**
 *Перевернуть массив.
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 0.1
 */
public class Turn {
    public int[] turn(int[] array) {
        for (int value = 0; value < array.length / 2; value++) {
            int param = array[value];
            array[value] = array[array.length - value - 1];
            array[array.length - value - 1] = param;
        }
        return array;
    }
}
