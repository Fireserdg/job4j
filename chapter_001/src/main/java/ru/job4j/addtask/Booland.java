package ru.job4j.addtask;
/**
 *Применение укороченных логических операторов.
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 06.05.2018
 */
public class Booland {
    /**
     *
     * @param a первое число.
     * @param b второе число
     * @return логическое выражение (true или false).
     */
    public boolean whatIsAnd(int a, int b) {
        return a > 5 && b > 5;
    }
    /**
     *
     * @param a первое число.
     * @param b второе число
     * @return логическое выражение (true или false).
     */
    public boolean whatIsOr(int a, int b) {
        return a % 5 == 0 || b % 5 == 0;
    }
    /**
     *
     * @param a первое число.
     * @param b второе число
     * @return логическое выражение (true или false).
     */
    public boolean checkOr(boolean a, boolean b) {
        return a || b;
    }
    /**
     *
     * @param a первое число.
     * @param b второе число
     * @return логическое выражение (true или false).
     */
    public boolean checkAnd(boolean a, boolean b) {
        return a && b;
    }
}
