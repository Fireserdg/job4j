package ru.job4j.json;

import org.junit.Test;
import ru.job4j.crud.models.Role;
import ru.job4j.crud.models.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Person test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 18.12.18
 */
public class PersonTest {

    @Test
    public void createPersonThenGetParam() {
        Person person = new Person();
        person.setId("1");
        person.setFirstName("First");
        person.setLastName("LastName");
        person.setSex("Male");
        person.setDesc("Desc");
        assertThat(person.getId(), is("1"));
        assertThat(person.getFirstName(), is("First"));
        assertThat(person.getLastName(), is("LastName"));
        assertThat(person.getSex(), is("Male"));
        assertThat(person.getDesc(), is("Desc"));
    }

    @Test
    public void whenComparingTwoPersons() {
        Person one = new Person();
        one.setId("1");
        one.setFirstName("First");
        one.setLastName("LastName");
        one.setSex("Male");
        one.setDesc("Desc");
        Person two = new Person();
        two.setId("2");
        two.setFirstName("First");
        two.setLastName("LastName");
        two.setSex("Male");
        two.setDesc("Desc");
        int oneHashCode = one.hashCode();
        int twoHashCode = two.hashCode();
        assertThat(one.equals(two), is(false));
        assertThat(oneHashCode == twoHashCode, is(false));
        two.setId("1");
        twoHashCode = two.hashCode();
        assertThat(one.equals(two), is(true));
        assertThat(oneHashCode == twoHashCode, is(true));
        Object user = new User("name", "login", "password",
                "email", System.currentTimeMillis(), Role.USER);
        assertThat(one.equals(user), is(false));
        two = one;
        assertThat(one.equals(two), is(true));
    }

    @Test
    public void whenGetToStringPerson() {
        Person person = new Person();
        person.setId("1");
        person.setFirstName("First");
        person.setLastName("LastName");
        person.setSex("Male");
        person.setDesc("Desc");
        String result = "Person {id=1, firstName=First, lastName=LastName, sex=Male, desc=Desc}";
        assertThat(person.toString(), is(result));
    }
}