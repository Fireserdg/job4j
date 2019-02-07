package ru.job4j.calculator;
/**
 * Программа расчета идеального веса.
 *  *@author Sergey Filippov (serdg1984@yandex.ru)
 *  *@version $Id$
 *  *@since 0.1
 */
public class Fit {
    /**
     * Идеальный вес для мужщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    private static final double R = 1.15;
    private static final int M = 100;
    private static final int W = 110;

    public double manWeight(double height) {
        return (height - M) * R;
    }
    /**
     * Идеальный вес для женщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double womanWeight(double height) {
        return (height - W) * R;
    }
}
