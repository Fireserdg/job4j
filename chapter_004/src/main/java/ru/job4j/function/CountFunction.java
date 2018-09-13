package ru.job4j.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Count function in diapason.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $1.0$.
 * @since 13.09.2018.
 */
public class CountFunction {

    /**
     * Count function in diapason.
     *
     * @param start start value.
     * @param end end value.
     * @param func function.
     * @return list of result.
     */
    public List<Double> diapason(int start, int end,
                                 BiFunction<Double, Double, Double> func) {
        List<Double> list = new ArrayList<>();
        for (int index = start; index <= end; index++) {
            list.add(func.apply((double) index, (double) end));
        }
        return list;
    }
}