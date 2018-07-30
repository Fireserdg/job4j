package ru.job4j.map;

import org.junit.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test method convert list to Map.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.07.2018.
 */
public class UserConvertTest {

    /**
     * Field contains object of PriorityQueue for Test method.
     */
    private UserConvert userConvert = new UserConvert();
    @Test
    public void whenConvertList3UserThenMap() {
        User userOne = new User(1, "Fedor", "Moscow");
        User userTwo = new User(3, "Alexandr", "Kaluga");
        User userThree = new User(14, "Ivan", "Tula");
        List<User> user = Arrays.asList(userOne, userTwo, userThree);
        HashMap<Integer, User> result = userConvert.process(user);
        assertThat(result.get(14).getName(), is("Ivan"));
    }

    @Test
    public void whenConvertList2UserThenMap() {
        User userOne = new User(4433, "Alexey", "Tver");
        User userTwo = new User(323, "Oleg", "Kaluga");
        List<User> user = Arrays.asList(userOne, userTwo);
        HashMap<Integer, User> result = userConvert.process(user);
        assertThat(result.get(323).getCity(), is("Kaluga"));
    }
}