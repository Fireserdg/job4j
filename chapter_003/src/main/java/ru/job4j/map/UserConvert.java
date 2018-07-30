package ru.job4j.map;

import java.util.HashMap;
import java.util.List;

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
        HashMap<Integer, User> result = new HashMap<>();
        for (User user: list) {
            result.put(user.getId(), new User(user.getName(), user.getCity()));
        }
        return result;
    }
}
