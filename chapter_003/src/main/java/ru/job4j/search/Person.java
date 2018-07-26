package ru.job4j.search;

/**
 * Phone book.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 26.07.2018.
 */
public class Person {

    /**
     * Field contains name;
     */
    private String name;
    /**
     * Field contains surname;
     */
    private String surname;
    /**
     * Field contains phone;
     */
    private String phone;
    /**
     * Field contains address;
     */
    private String address;

    /**
     * Class constructor that accepts input parameters.
     *
     * @param name name new Person.
     * @param surname surname new Person.
     * @param phone phone new Person.
     * @param address address new Person.
     */
    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Get name.
     *
     * @return name Person.
     */
    public String getName() {
        return name;
    }

    /**
     * Get surname Person.
     *
     * @return surname Person.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Get phone Person.
     *
     * @return phone Person.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Get address Person.
     *
     * @return address Person.
     */
    public String getAddress() {
        return address;
    }

    public String toString() {
        return String.format("%s, %s, %s, %s", getName(), getSurname(), getPhone(), getAddress());
    }
}