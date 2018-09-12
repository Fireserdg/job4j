package ru.job4j.calculator;

import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Calculator.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $1.0$.
 * @since 12.09.2018.
 */
public class Calculator {

    /**
     * Operation with values.
     *
     * @param start start position.
     * @param finish finish position.
     * @param value value
     * @param op function for operation.
     * @param media function for operation.
     */
    public void multiple(int start, int finish, int value,
                         BiFunction<Integer, Integer, Double> op,
                         Consumer<Double> media) {
        for (int index = start; index != finish; index++) {
            media.accept(op.apply(value, index));
        }
    }
}