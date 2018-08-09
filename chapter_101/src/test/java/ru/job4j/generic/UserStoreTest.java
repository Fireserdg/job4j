package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class for User Store.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 09.08.2018.
 */
public class UserStoreTest {

    /**
     * Contains users list.
     *
     */
    private Store<User> users;

    /**
     * Create list users.
     *
     */
    @Before
    public void createUsers() {
        users = new UserStore<>(3);
        User userOne = new User("1", "Sergey", 20);
        User userTwo = new User("2", "Andrey", 22);
        User userThree = new User("3", "Victor", 24);
        users.add(userOne);
        users.add(userTwo);
        users.add(userThree);
    }
    @Test
    public void whenAddNewUserInUserStore() {
        String result = users.findById("3").getName();
        assertThat(result, is("Victor"));
    }

    @Test
    public void whenFindByIdUserAndResultTrue() {
        int result = users.findById("2").getAge();
        assertThat(result, is(22));
    }

    @Test
    public void whenDeleteUserFromUserStore() {
        boolean result = users.delete("2");
        assertThat(result, is(true));
    }

    @Test
    public void whenReplaceRoleInRoleStorage() {
        User user = new User("33", "Pavel", 45);
        boolean result = users.replace("2", user);
        assertThat(result, is(true));
        assertThat(users.findById("33").getName(), is("Pavel"));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenFindUserByIdAndGetException() {
        users.findById("10");
    }
}