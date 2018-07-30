package ru.job4j.sort;

/**
 * Class User for sort.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.07.2018.
 */

public class User implements Comparable<User> {
    /**
     * Field contains name;
     */
    private String name;
    /**
     * Field contains age;
     */
    private int age;

    /**
     * Class constructor that accepts input parameters.
     *
     * @param name name new Person.
     * @param age age new Person.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(User o) {
        return Integer.compare(this.age, o.age);
    }
}
