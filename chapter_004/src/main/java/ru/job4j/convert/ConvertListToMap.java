package ru.job4j.convert;


import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Convert list to map.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 03.02.19
 */
public class ConvertListToMap {

    /**
     * Convert list of students.
     *
     * @param students list of students.
     * @return map of students
     */
    public Map<String, Student> collect(List<Student> students) {
        return students.stream().collect(Collectors.toMap(Student::getName, Function.identity()));
    }
}