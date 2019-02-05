package ru.job4j.filters;


import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * School
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 02.02.19
 */
public class School {

    /**
     * Getting a list of students with specific conditions.
     *
     * @param students  list of students.
     * @param predicate predicate for list students.
     * @return specific list of students.
     */
    public List<Students> collect(List<Students> students, Predicate<Students> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }
}