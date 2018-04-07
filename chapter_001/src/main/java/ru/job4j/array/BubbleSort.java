package ru.job4j.array;
/**
 *Сортировка методом перестановки.
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 0.1
 */
public class BubbleSort {

    public int[] sort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int a = array [i];
            for (int j = i - 1; j >= 0; j--) {
                int b = array [j];
                if (a < b) {
                    array[j + 1] = b;
                    array[j] = a;
                } else {
                    break;
                }
            }
        }
        return array;
    }
}
