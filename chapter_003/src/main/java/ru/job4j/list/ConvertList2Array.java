package ru.job4j.list;

import java.util.List;

/**
 * Convert list for array.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 27.07.2018.
 */
public class ConvertList2Array {

    /**
     * Getting a two-dimensional array from a list.
     *
     * @param list array of numbers.
     * @param rows number of line.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % rows;

        if (cells != 0) {
            cells = (int) Math.ceil((double) list.size() / rows);
        } else {
            cells = list.size() / rows;
        }

        int[][] array = new int[rows][cells];
        int count = 0, index = 0;
        for (int[] arr: array) {
            for (int in: arr) {
                if (!(index == list.size())) {
                    in = list.get(index++);
                    arr[count++] = in;
                } else {
                    break;
                }
            }
            count = 0;
        }
        return array;
    }
}