package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Phone book.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 26.07.2018.
 */
public class PhoneDictionary {

    /**
     * Field contains List Person.
     */
    private List<Person> persons = new ArrayList<>();

    /**
     * Add new Person in List.
     *
     * @param person new Person.
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Return a list of all persons that contains key in any fields.
     *
     * @param key Key for find.
     * @return List appropriate persons.
     */
    public List<Person> find(String key) {
        return this.persons.stream().filter(
                n -> n.toString().contains(key)).collect(Collectors.toList());
    }
}
