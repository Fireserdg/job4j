package ru.job4j.control;

import java.util.*;

/**
 * Store collections.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 20.08.2018.
 */
public class Store {

    /**
     * The difference in collections.
     *
     * @param previous previous list Users.
     * @param current current list Users.
     * @return information about difference.
     */
    public Info diff(List<User> previous, List<User> current) {
        int count = 0;
        int change = 0;
        for (User prv: previous) {
            for (User crt: current) {
                if (prv.getId() == crt.getId()) {
                    count++;
                }
                if (prv.getId() == crt.getId()
                        && !(prv.getName().equals(crt.getName()))) {
                    change++;
                }
            }
        }
        int addNew = current.size() - count;
        int delete = previous.size() - count;
        return new Info(addNew, delete, change);
    }

    /**
     * Inner class User.
     *
     */
    static class User {

        /**
         * Contains User id.
         *
         */
        private int id;

        /**
         * Contains User name.
         *
         */
        private String name;

        /**
         * Constructor User.
         *
         * @param id new User id
         * @param name new User name.
         */
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.format("User: id=%s, name=%s.", this.id, this.name);
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
            return this.id == user.id
                    && Objects.equals(this.name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.id, this.name);
        }
    }
}