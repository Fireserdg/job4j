package ru.job4j.search;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Phone book.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 26.07.2018.
 */
public class PhoneDictionaryTest {

    private PhoneDictionary phones = new PhoneDictionary();

    @Test
    public void whenFindByName() {
        phones.add(new Person("Sergey", "Philippov", "5628456", "Moscow"));
        List<Person> persons = phones.find("Ser");
        assertThat(persons.iterator().next().getSurname(), is("Philippov"));
    }

    @Test
    public void whenFindByAddress() {
        phones.add(new Person("Ivan", "Ivanov", "677832", "Tula"));
        phones.add(new Person("Alexandr", "Petrov", "456222", "Tver"));
        List<Person> persons = phones.find("ula");
        assertThat(persons.iterator().next().getName(), is("Ivan"));
    }

    @Test
    public void whenFindByPhone() {
        phones.add(new Person("Ivan", "Egorov", "346832", "Orel"));
        phones.add(new Person("Petr", "Lomov", "677234", "Kaluga"));
        List<Person> persons = phones.find("683");
        assertThat(persons.iterator().next().getAddress(), is("Orel"));
    }

    @Test
    public void whenFindBySurname() {
        phones.add(new Person("Oleg", "Fedorov", "123456", "Penza"));
        phones.add(new Person("Petr", "Lomov", "673982", "Kirov"));
        List<Person> persons = phones.find("dorov");
        assertThat(persons.iterator().next().getPhone(), is("123456"));
    }
}