package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test User.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 13.08.2018.
 */
public class UserTest {

    @Test
    public void whenCreatingTwoUserWithoutOverrideHashcodeAndEquals() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1984, Calendar.FEBRUARY, 12);
        User first = new User("Иван", 2, calendar);
        User second = new User("Иван", 2, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(first, 1);
        map.put(second, 2);
        User third = null;
        int hash = first.hashCode();
        int hash2 = second.hashCode();
        assertThat(hash2 == hash, is(true));
        assertThat(first.equals(second), is(true));
        assertThat(second.equals(third), is(false));
    }
}