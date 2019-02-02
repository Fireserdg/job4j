package ru.job4j.convert;

import java.util.Objects;

/**
 * Student
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 03.02.19
 */
public class Student {

    /**
     * Student name.
     */
    private String name;

    /**
     * Constructor student.
     *
     * @param name name
     */
    public Student(String name) {
        this.name = name;
    }

    /**
     * Get name student.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Equals between two objects
     *
     * @param o other object
     * @return true if other object the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    /**
     * Hashcode.
     *
     * @return value hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}