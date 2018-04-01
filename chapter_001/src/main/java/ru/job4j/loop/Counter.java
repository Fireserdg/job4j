package ru.job4j.loop;
/**
 * Подсчет суммы четных чисел в диапазоне.
 *  *@author Sergey Filippov (serdg1984@yandex.ru)
 *  *@version $Id$
 *  *@since 0.1
 */
public class Counter {
    /**
     * Метод вычисления суммы четных чисел в диапазоне.
     *
     * @param start начальное число.
     * @param finish конечное число.
     * @return сумма четных чисел.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
