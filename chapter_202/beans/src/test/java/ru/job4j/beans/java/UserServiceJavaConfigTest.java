package ru.job4j.beans.java;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.beans.entity.User;
import ru.job4j.beans.service.UserService;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * UserServiceJavaConfigTest
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-05-05
 */
public class UserServiceJavaConfigTest {

    private UserService userService;

    @Before
    public void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(UserServiceConfiguration.class);
        userService = context.getBean(UserServiceJavaConfig.class);
    }

    @Test
    public void whenLoadContextShouldGetBean() {
        assertNotNull(userService);
    }

    @Test
    public void whenAddUserMemoryStorageThenFindUserById() {
        userService.add(new User("Vasia"));
        assertThat("Vasia", is(userService.findUserById(1L).getName()));
    }
}