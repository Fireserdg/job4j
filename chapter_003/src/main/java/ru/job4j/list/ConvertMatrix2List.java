package ru.job4j.list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return Arrays.stream(array).flatMapToInt(IntStream::of).boxed()
                .collect(Collectors.toList());
    }
}