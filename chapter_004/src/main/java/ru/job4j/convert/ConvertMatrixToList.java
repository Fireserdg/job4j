package ru.job4j.convert;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ConvertMatrixToList
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 03.02.19
 */
public class ConvertMatrixToList {

    /**
     * Convert matrix to list.
     *
     * @param matrix matrix.
     * @return list of numbers.
     */
    public List<Integer> convertToList(Integer[][] matrix) {
        return Arrays.stream(matrix).flatMap(Arrays::stream).collect(Collectors.toList());
    }
}
