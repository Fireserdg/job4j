package ru.job4j.beans.annotation;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.job4j.beans.entity.User;
import ru.job4j.beans.service.UserService;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * UserServiceAnnotationTest
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-05-04
 */
public class UserServiceAnnotationTest {

    private UserService userService;

    @Before
    public void setUp() {
        ApplicationContext context = new GenericXmlApplicationContext(
                "classpath:spring/spring-context-annotation.xml");
        userService = context.getBean(UserServiceAnnotation.class);
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