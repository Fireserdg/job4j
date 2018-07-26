package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

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
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (person.toString().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }
}
