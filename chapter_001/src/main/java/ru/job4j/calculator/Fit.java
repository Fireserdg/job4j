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
    public double manWeight(double height) {
        double idealManWeight = (height - 100) * 1.15;
        return idealManWeight;
    }
    /**
     * Идеальный вес для женщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double womanWeight(double height) {
        double idealWomanWeight = (height - 110) * 1.15;
        return idealWomanWeight;
    }
}
