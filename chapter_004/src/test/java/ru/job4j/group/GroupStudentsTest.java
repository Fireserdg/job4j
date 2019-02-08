package ru.job4j.group;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test for group students.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 08.02.19
 */
public class GroupStudentsTest {

    @Test
    public void whenGetGroupStudentsThenGetListStudentsBasedOnScore() {
        List<Student> students = Arrays.asList(
                new Student("Kirill", 45), new Student("Bill", 77),
                new Student("Den", 90), new Student("Lika", 51));
        List<Student> result = new GroupStudents().levelOf(students, 52);
        assertThat(result.size(), is(2));
        assertThat(result.get(0).getName(), is("Den"));
        assertThat(result.get(1).getScope(), is(77));
    }

    @Test
    public void whenGetGroupStudentsWithNullElThenGetListStudentsBasedOnScore() {
        List<Student> students = Arrays.asList(
                new Student("Kirill", 45), null, new Student("Den", 90),
                null, new Student("Lika", 51));
        List<Student> result = new GroupStudents().levelOf(students, 47);
        assertThat(result.size(), is(2));
        assertThat(result.get(0).getName(), is("Den"));
        assertThat(result.get(1).getScope(), is(51));
    }

    @Test
    public void whenGetToStringAndCompareForStudent() {
        Student first = new Student("Kirill", 45);
        Student second = new Student("Den", 90);
        String expected = "Student{name=Kirill, scope=45}";
        int exp = first.compareTo(second);
        assertThat(first.toString(), is(expected));
        assertThat(exp, is(-1));
    }
}