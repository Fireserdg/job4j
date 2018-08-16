package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Class User.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 13.08.2018.
 */
public class User {

    /**
     * Contains User name.
     *
     */
    private String name;

    /**
     * Contains count User children.
     *
     */
    private int children;

    /**
     * Contains User birthday.
     *
     */
    private Calendar birthday;

    /**
     * Constructor User.
     *
     * @param name name of new User.
     * @param children count children of new User.
     * @param birthday birthday of new User.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, children, birthday);
    }
}