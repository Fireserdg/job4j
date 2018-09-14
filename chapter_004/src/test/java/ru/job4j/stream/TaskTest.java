package ru.job4j.stream;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Task test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $1.0$
 * @since 14.09.2018.
 */
public class TaskTest {

    /**
     * Contains list of task.
     *
     */
    private List<Task> tasks;

    /**
     * Create object for test.
     *
     */
    @Before
    public void createObject() {
        this.tasks = Arrays.asList(new Task("Bug #1", 100),
                new Task("Task #2", 100),
                new Task("Bug #3", 100));
    }

    @Test
    public void whenUseStreamFilterThenGetNewListContainsBugName() {
        List<Task> bag = tasks.stream().filter(
                task -> task.getName().contains("Bug")).
                collect(Collectors.toList());
        assertThat(bag.get(0).getName(), is("Bug #1"));
        assertThat(bag.get(1).getName(), is("Bug #3"));
    }

    @Test
    public void whenUseStreamMapThenGetListName() {
        List<String> names = tasks.stream().map(task -> task.getName()).
                collect(Collectors.toList());
        List<String> expected = Arrays.asList("Bug #1", "Task #2", "Bug #3");
        assertThat(names, is(expected));
    }

    @Test
    public void whenUseStreamReduceThenGetSumTasks() {
        long result = tasks.stream().map(task -> task.getSpent()).
                reduce(0L, Long::sum);
        long expected = 300L;
        assertThat(result, is(expected));
    }
}