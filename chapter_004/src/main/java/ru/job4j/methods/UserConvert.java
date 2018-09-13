package ru.job4j.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * User Convert.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $1.0$.
 * @since 13.09.2018.
 */
public class UserConvert {

    /**
     * Convert name to users.
     *
     * @param names names of users
     * @param op function for convert.
     * @return list of users.
     */
    public List<User> convert(List<String> names, Function<String, User> op) {
        List<User> users = new ArrayList<>();
        names.forEach(n -> users.add(op.apply(n)));
        return users;
    }

    /**
     * User.
     *
     */
    public static class User {

        /**
         * Contains name.
         */
        private final String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.format("User{name=%s}", name);
        }
    }
}
