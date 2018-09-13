package ru.job4j.methods;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test User convert.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $1.0$
 * @since 13.09.2018.
 */
public class UserConvertTest {

    @Test
    public void whenCreate3namesThenCreate3Users() {
        List<String> names = Arrays.asList("Petr", "Nick", "Ban");
        UserConvert users = new UserConvert();
        List<UserConvert.User> data = users.convert(names, UserConvert.User::new);
        assertThat(data.get(0).getName(), is("Petr"));
        assertThat(data.get(1).getName(), is("Nick"));
        assertThat(data.get(2).getName(), is("Ban"));
    }
}