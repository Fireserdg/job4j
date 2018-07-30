package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Convert Matrix array in list.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.07.2018.
 */
public class ConvertMatrix2List {

    /**
     * Getting of list numbers from an array.
     *
     * @param array arrays of numbers.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] arrayArrays: array) {
            for (int input: arrayArrays) {
                list.add(input);
            }
        }
        return list;
    }
}