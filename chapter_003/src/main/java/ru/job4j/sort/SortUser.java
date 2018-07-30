package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Sort User.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.07.2018.
 */
public class SortUser {

    /**
     * Sort list of Users in TreeSet.
     *
     * @param list list Users.
     * @return sort Users.
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }
}
