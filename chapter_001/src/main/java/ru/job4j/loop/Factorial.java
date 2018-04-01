package ru.job4j.loop;
/**
 * Программа для вычисления факториала.
 *  *@author Sergey Filippov (serdg1984@yandex.ru)
 *  *@version $Id$
 *  *@since 0.1
 */
public class Factorial {
    /**
     * Метод вычисления факториала.
     *
     * @param n целое число.
     * @return возвращаем факториал для заданного числа.
     * @return факториал для 0 возвращает единицу.
     */
    public int calc(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
