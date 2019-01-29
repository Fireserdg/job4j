package ru.job4j.crud.store;

import org.junit.Test;
import ru.job4j.crud.models.Role;
import ru.job4j.crud.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * MemoryStore Test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.12.18
 */
public class MemoryStoreTest {

    @Test
    public void whenCrudOperationUsers() {
        MemoryStore memory = MemoryStore.getInstance();
        User user = new User("name", "login",
                "password", "email",
                System.currentTimeMillis(), Role.USER,
                "Russia", "Moscow");
        memory.add(user);
        assertThat(memory.findAll().size(), is(1));
        assertThat(memory.findAll().get(0).getName(), is("name"));
        assertThat(memory.findAll().get(0).getLogin(), is("login"));
        assertThat(memory.findAll().get(0).getPassword(), is("password"));
        assertThat(memory.findAll().get(0).getId(), is("1"));
        assertThat(memory.findById("1").getRole(), is(Role.USER));
        User userUpdate = new User(
                "1", "newName", "newLogin",
                "newPassword", "email", user.getCreate(), user.getRole(),
                "USA", "New-York");
        memory.update(userUpdate);
        assertThat(memory.findAll().get(0).getName(), is("newName"));
        assertThat(memory.findAll().get(0).getLogin(), is("newLogin"));
        memory.delete("1");
        assertThat(memory.findAll().size(), is(0));
        List<String> country = Arrays.asList("Russia", "USA", "France");
        List<String> result = new ArrayList<>(memory.getCountry().values());
        assertThat(country, is(result));
        List<String> city = Arrays.asList("Moscow", "Tver", "Kazan", "Voronezh");
        List<String> cityResult = memory.getCity("1");
        assertThat(city, is(cityResult));
    }
}