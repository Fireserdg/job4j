package ru.job4j.group;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Group students.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 08.02.19
 */
public class GroupStudents {

    /**
     * Get list of students by scope.
     *
     * @param list list of students.
     * @param bound bound.
     * @return list of students by scope.
     */
    public List<Student> levelOf(List<Student> list, int bound) {
        return list.stream()
                .flatMap(Stream::ofNullable)
                .sorted(Comparator.reverseOrder())
                .takeWhile(student -> student.getScope() > bound)
                .collect(Collectors.toList());
    }
}
