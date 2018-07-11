package ru.job4j.patternstrategy;
/**
 * Реализация метода draw() в классе Square.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 11.07.2018.
 */
public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder sb = new StringBuilder();
        sb.append("++++");
        sb.append("+  +");
        sb.append("+  +");
        sb.append("++++");
        return sb.toString();
    }
}
