package ru.job4j.map;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Convert list to Map.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.07.2018.
 */
public class UserConvert {

    /**
     * Getting of Map User from list.
     *
     * @param list list of Users.
     */
    public HashMap<Integer, User> process(List<User> list) {
        return new HashMap<>(list.stream().collect(Collectors
                .toMap(User::getId, n -> new User(n.getName(), n.getCity()))));
    }
}
