package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Integer> result = new ArrayList<>(list);
        return Arrays.stream(new int[rows][(int) Math.ceil((double) list.size() / rows)])
                .map(n -> Arrays.stream(n).map(k -> result.isEmpty() ? 0
                        : result.remove(0)).toArray()).toArray(int[][]::new);
    }

    /**
     * Getting a list of numbers from list of arrays.
     *
     * @param list arrays of numbers.
     */
    public List<Integer> convert(List<int[]> list) {
        return list.stream().flatMapToInt(IntStream::of)
                .boxed().collect(Collectors.toList());
    }
}