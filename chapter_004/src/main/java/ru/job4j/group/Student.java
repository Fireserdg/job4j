package ru.job4j.group;

/**
 * Student
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 08.02.19
 */
public class Student implements Comparable<Student> {

    /**
     * Student name.
     */
    private String name;

    /**
     * Student scope.
     */
    private int scope;

    /**
     * Constructor student.
     *
     * @param name  student name.
     * @param scope student scope.
     */
    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    /**
     * Get name student.
     *
     * @return name student.
     */
    public String getName() {
        return name;
    }

    /**
     * Get scope student.
     *
     * @return scope.
     */
    public int getScope() {
        return scope;
    }

    /**
     * Compare with other student.
     *
     * @param o other student.
     * @return result compare.
     */
    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.scope, o.scope);
    }

    /**
     * String representation of an objectю
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return String.format("Student{name=%s, scope=%d}", this.name, this.scope);
    }
}
