package ru.job4j.map;

import java.util.Objects;

/**
 * Class User.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.07.2018.
 */
public class User {

    /**
     * Field contains User id.
     */
    private int id;
    /**
     * Field contains User name.
     */
    private String name;
    /**
     * Field contains User city.
     */
    private String city;

    /**
     * Class constructor that accepts input parameters.
     *
     * @param id id new User.
     * @param name name new User.
     * @param city city new User.
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Class constructor that accepts input parameters for Map.
     *
     * @param name name new User.
     * @param city city new User.
     */
    public User(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
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
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
