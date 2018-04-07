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

        for (int i = 0; i < array.length / 2; i++) {
            int a = array [i];
            array [i] = array [array.length - i - 1];
            array [array.length - i - 1] = a;
        }
        return array;
    }


}
