package ru.job4j.crud.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.Validate;
import ru.job4j.crud.ValidateService;
import ru.job4j.crud.ValidateStub;
import ru.job4j.crud.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;


/**
 * User controller test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 05.12.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UsersControllerServletTest {

    @Test
    public void whenCrudUserThenStoreIt() throws IOException, ServletException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        List<String[]> params = Arrays.asList(
                new String[]{"action", "add"},
                new String[]{"name", "Sergey"},
                new String[]{"login", "login123"},
                new String[]{"password", "123"},
                new String[]{"email", "email@gmail.com"},
                new String[]{"role", "USER"}
        );
        Map<String, String[]> value = params.stream().collect(
                Collectors.toMap(attr -> attr[0],
                        param -> new String[]{param[1]}, (
                                v1, v2) -> v1, LinkedHashMap::new));
        when(req.getParameterMap()).thenReturn(value);
        when(req.getParameter("action")).thenReturn("add");
        when(req.getRequestDispatcher("/WEB-INF/view/answer.jsp"))
                .thenReturn(requestDispatcher);
        new UsersControllerServlet().doPost(req, resp);
        List<User> users = validate.findAll();
        verify(requestDispatcher).forward(req, resp);
        verify(req, Mockito.times(1)).getSession();
        assertThat(users.get(0).getName(), is("Sergey"));
        assertThat(users.get(0).getLogin(), is("login123"));
        when(req.getParameter("action")).thenReturn("update");
        when(req.getRequestDispatcher("/WEB-INF/view/answer.jsp"))
                .thenReturn(requestDispatcher);
        params = Arrays.asList(
                new String[]{"action", "update"},
                new String[]{"id", "1"},
                new String[]{"name", "Victor"},
                new String[]{"login", "newLogin"},
                new String[]{"password", "123"},
                new String[]{"email", "newEmail@gmail.com"},
                new String[]{"role", "USER"}
        );
        value = params.stream().collect(
                Collectors.toMap(attr -> attr[0],
                        param -> new String[]{param[1]}, (
                                v1, v2) -> v1, LinkedHashMap::new));
        when(req.getParameterMap()).thenReturn(value);
        new UsersControllerServlet().doPost(req, resp);
        users = validate.findAll();
        assertThat(users.get(0).getName(), is("Victor"));
        assertThat(users.get(0).getLogin(), is("newLogin"));
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn("1");
        when(req.getRequestDispatcher("/WEB-INF/view/main.jsp")).thenReturn(requestDispatcher);
        new UsersControllerServlet().doGet(req, resp);
        verify(requestDispatcher, Mockito.times(3)).forward(req, resp);
        params = Arrays.asList(
                new String[]{"action", "delete"},
                new String[]{"id", "1"}
        );
        value = params.stream().collect(
                Collectors.toMap(attr -> attr[0],
                        param -> new String[]{param[1]}, (a, b) -> a, LinkedHashMap::new));
        when(req.getParameterMap()).thenReturn(value);
        new UsersControllerServlet().doPost(req, resp);
        assertThat(validate.findAll().size(), is(0));
    }
}