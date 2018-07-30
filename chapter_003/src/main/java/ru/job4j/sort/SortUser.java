package ru.job4j.sort;

import java.util.*;

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
        List<User> users = new ArrayList<>(list);
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });
        return users;
    }

    /**
     * Sort list of Users by name and age.
     *
     * @param list of Users.
     * @return list of sort Users.
     */
    public List<User> sortByAllFields(List<User> list) {
        List<User> users = new ArrayList<>(list);
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().compareTo(o2.getName());
                if (result == 0) {
                    result = Integer.compare(o1.getAge(), o2.getAge());
                }
                return result;
            }
        });
        return users;
    }
}
