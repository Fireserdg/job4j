package ru.job4j.crud;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test model for User.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 23.11.18
 */
public class UserTest {

    @Test
    public void whenCreateUserThenGetField() {
        long time = System.currentTimeMillis();
        User user = new User("1", "name", "login",
                "email", time);
        assertThat(user.getName(), is("name"));
        assertThat(user.getLogin(), is("login"));
        assertThat(user.getEmail(), is("email"));
        assertThat(user.getId(), is("1"));
        assertThat(user.getCreate(), is(time));
    }

    @Test
    public void whenCreateUsersThenUpdateAndCheckEqualsAndHashCode() {
        long time = System.currentTimeMillis();
        User one = new User("1", "name", "login",
                "email", time);
        User theSame = new User("1", "newName", "newLogin",
                "NewEmail", time);
        assertThat(one.equals(theSame), is(true));
        assertThat(one.hashCode() == theSame.hashCode(), is(true));
    }
}