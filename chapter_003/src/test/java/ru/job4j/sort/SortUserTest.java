package ru.job4j.sort;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test sort Users.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.07.2018.
 */

public class SortUserTest {

    /**
     * Field contains object of PriorityQueue for Test method.
     */
    private SortUser sort = new SortUser();

    @Test
    public void whenAdd3UsersThenSortAge() {
        User userOne = new User("Ivan", 30);
        User userTwo = new User("Fedor", 25);
        User userThree = new User("Max", 27);
        List<User> list = Arrays.asList(userOne, userTwo, userThree);
        Set<User> result = sort.sort(list);
        assertThat(result.iterator().next(), is(userTwo));
    }

    @Test
    public void whenAdd4UsersThenSortAge() {
        User userOne = new User("Alexey", 45);
        User userTwo = new User("Stepan", 28);
        User userThree = new User("Nikita", 33);
        User userFour = new User("Gena", 25);
        List<User> list = Arrays.asList(userOne, userTwo, userThree, userFour);
        Set<User> result = sort.sort(list);
        assertThat(result.iterator().next().getName(), is("Gena"));
    }

    @Test
    public void whenSort3UserByNameLength() {
        List<User> users = Arrays.asList(new User("Alexandr", 25),
                new User("Roma", 13), new User("Stepan", 20));
        List<User> result = sort.sortNameLength(users);
        assertThat(result.get(0).getName(), is("Roma"));
    }

    @Test
    public void whenSort4UserByNameAndByAge() {
        List<User> users = Arrays.asList(new User("Sergey", 25),
                new User("Ivan", 30), new User("Sergey", 20),
                new User("Ivan", 25));
        List<User> result = sort.sortByAllFields(users);
        assertThat(result.get(0).toString(), is("User name: Ivan, age: 25."));
    }
}