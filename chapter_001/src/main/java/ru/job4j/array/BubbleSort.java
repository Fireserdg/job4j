package ru.job4j.array;
/**
 *Сортировка методом перестановки.
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 0.1
 */
public class BubbleSort {
    /**
     *
     * @param array
     * @return sorted array.
     */
    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int index = array [i];
            for (int j = i - 1; j >= 0; j--) {
                int data = array [j];
                if (index < data) {
                    array[j + 1] = data;
                    array[j] = index;
                } else {
                    break;
                }
            }
        }
        return array;
    }
}
