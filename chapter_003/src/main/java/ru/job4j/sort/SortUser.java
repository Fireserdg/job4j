package ru.job4j.sort;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * Sort list User by name length.
     *
     * @param list list of Users.
     * @return list of sort Users.
     */
    public List<User> sortNameLength(List<User> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(u -> u.getName().length()))
                .collect(Collectors.toList());
    }

    /**
     * Sort list of Users by name and age.
     *
     * @param list of Users.
     * @return list of sort Users.
     */
    public List<User> sortByAllFields(List<User> list) {
        return list.stream()
                .sorted((u1, u2) -> u1.getName().compareTo(u2.getName()) == 0
                ? Integer.compare(u1.getAge(), u2.getAge()) : u1.getName()
                        .compareTo(u2.getName())).collect(Collectors.toList());
    }
}
