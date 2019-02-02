package ru.job4j.convert;

import org.junit.Test;
import ru.job4j.filtres.Students;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test convert
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 03.02.19
 */
public class ConvertListToMapTest {

    @Test
    public void whenConvertListStudentsToMapStudents() {
        Student first = new Student("Mike");
        Student second = new Student("Bill");
        Student third = new Student("Gena");
        List<Student> list = Arrays.asList(first, second, third);
        Map<String, Student> map = new ConvertListToMap().collect(list);
        assertThat(map.containsKey("Bill"), is(true));
        assertThat(map.containsKey("Gena"), is(true));
        assertThat(map.get("Mike"), is(first));
    }

    @Test
    public void whenUseEqualsAndHashCode() {
        Student first = new Student("Mike");
        Student second = new Student("Bill");
        Student third = new Student("Mike");
        assertThat(first.equals(second), is(false));
        assertThat(first.equals(third), is(true));
        assertThat(first.hashCode() != second.hashCode(), is(true));
        assertThat(first.hashCode() == third.hashCode(), is(true));
        assertThat(first.equals(new Students(1)), is(false));
        third = null;
        assertThat(first.equals(third), is(false));
    }
}