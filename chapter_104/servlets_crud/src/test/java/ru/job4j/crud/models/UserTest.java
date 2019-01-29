package ru.job4j.crud.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test model for Role.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 23.11.18
 */
public class UserTest {

    @Test
    public void whenCreateUserThenGetField() {
        long time = System.currentTimeMillis();
        User user = new User("1", "name", "login", "123",
                "email", time, Role.USER, "Russia", "Moscow");
        assertThat(user.getName(), is("name"));
        assertThat(user.getLogin(), is("login"));
        assertThat(user.getEmail(), is("email"));
        assertThat(user.getId(), is("1"));
        assertThat(user.getCreate(), is(time));
        assertThat(user.getRole(), is(Role.USER));
        assertThat(user.getCountry(), is("Russia"));
        assertThat(user.getCity(), is("Moscow"));
    }

    @Test
    public void whenCreateUsersThenUpdateAndCheckEqualsAndHashCode() {
        long time = System.currentTimeMillis();
        User one = new User("1", "name", "login", "123",
                "email", time, Role.ADMIN, "USA", "Ney-York");
        User theSame = new User("1", "newName", "newLogin", "333",
                "NewEmail", time, Role.ADMIN, "France", "Paris");
        String output = "User[id=%s, name=%s, login=%s, email=%s, create=%s]";
        assertThat(one.equals(theSame), is(true));
        assertThat(one.hashCode() == theSame.hashCode(), is(true));
        assertThat(one.toString(), is(String.format(
                output, one.getId(), one.getName(),
                one.getLogin(), one.getEmail(), one.getDate())));
    }
}