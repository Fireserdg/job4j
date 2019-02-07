package ru.job4j.patternstrategy;

/**
 * Реализация класса Paint.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 11.07.2018.
 */
public class Paint {
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

}
