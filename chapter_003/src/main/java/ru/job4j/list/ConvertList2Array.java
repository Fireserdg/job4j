package ru.job4j.list;

import java.util.ArrayList;
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
        List<Integer> arrayList = new ArrayList<>(list);
        int cells = arrayList.size() % rows;

        while (true) {
            if (cells == 0) {
                cells = arrayList.size() / rows;
                break;
            }
            arrayList.add(0);
            cells = arrayList.size() % rows;
        }

        int[][] array = new int[rows][cells];
        int count = 0, index = 0;
        for (int[] arr: array) {
            for (int in: arr) {
                in = arrayList.get(index++);
                arr[count++] = in;
            }
            count = 0;
        }
        return array;
    }
}