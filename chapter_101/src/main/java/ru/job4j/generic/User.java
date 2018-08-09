package ru.job4j.generic;

/**
 * Class User.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 09.08.2018.
 */
public class User extends Base {

    /**
     * Contains User name.
     *
     */
    private final String name;

    /**
     * Contains User age.
     *
     */
    private final int age;

    /**
     * Constructor User.
     *
     * @param id id new User.
     * @param name name new User.
     * @param age age new User.
     */
    protected User(String id, final String name, final int age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("User{name=%s, age= %s}", name, age);
    }
}
