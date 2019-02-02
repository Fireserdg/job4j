package ru.job4j.filtres;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test for filter school.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 02.02.19
 */
public class SchoolTest {

    /**
     * Contains list of students.
     */
    private List<Students> studentsList = new ArrayList<>();

    /**
     * Init students list.
     */
    @Before
    public void initList() {
        for (int i = 0; i <= 100; i += 5) {
            studentsList.add(new Students(i));
        }
    }

    @Test
    public void whenGetListStudentsWithScoreBetweenScore70to100() {
        List<Students> result = new School().collect(studentsList,
                students -> students.getScore() >= 70 && students.getScore() <= 100);
        assertThat(result.size(), is(7));
        assertThat(result.get(0).getScore(), is(70));
        assertThat(result.get(result.size() - 1).getScore(), is(100));
    }

    @Test
    public void whenGetListStudentsWithScoreBetweenScore50to70() {
        List<Students> result = new School().collect(studentsList,
                students -> students.getScore() >= 50 && students.getScore() < 70);
        assertThat(result.size(), is(4));
        assertThat(result.get(0).getScore(), is(50));
        assertThat(result.get(result.size() - 1).getScore(), is(65));
    }

    @Test
    public void whenGetListStudentsWithScoreBetweenScore0to50() {
        List<Students> result = new School().collect(studentsList,
                students -> students.getScore() >= 0 && students.getScore() < 50);
        assertThat(result.size(), is(10));
        assertThat(result.get(0).getScore(), is(0));
        assertThat(result.get(result.size() - 1).getScore(), is(45));
    }
}