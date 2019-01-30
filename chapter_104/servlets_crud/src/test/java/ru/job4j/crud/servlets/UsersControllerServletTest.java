package ru.job4j.crud.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.Validate;
import ru.job4j.crud.ValidateService;
import ru.job4j.crud.ValidateStub;
import ru.job4j.crud.models.Role;
import ru.job4j.crud.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;


/**
 * User controller test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 05.12.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
@PowerMockIgnore({"javax.net.ssl.*", "javax.security.*"})
public class UsersControllerServletTest {

    @Test
    public void whenCrudUserThenStoreIt() throws IOException, ServletException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        BufferedReader reader = new BufferedReader(new StringReader(
                getJsonAddUser()));
        when(req.getReader()).thenReturn(reader);
        PrintWriter writer = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(writer);
        new UsersControllerServlet().doPost(req, resp);
        List<User> users = validate.findAll();
        assertThat(users.get(0).getName(), is("Ivan"));
        assertThat(users.get(0).getLogin(), is("login"));
        assertThat(users.get(0).getCountry(), is("USA"));

        reader = new BufferedReader(new StringReader(getJsonUpdateUser()));
        when(req.getReader()).thenReturn(reader);
        new UsersControllerServlet().doPost(req, resp);
        users = validate.findAll();
        assertThat(users.get(0).getLogin(), is("newLogin"));
        assertThat(users.get(0).getEmail(), is("333@gmail.com"));
        assertThat(users.get(0).getRole(), is(Role.ADMIN));

        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn("1");
        when(req.getRequestDispatcher("/WEB-INF/view/main.jsp"))
                .thenReturn(requestDispatcher);
        new UsersControllerServlet().doGet(req, resp);
        verify(requestDispatcher, Mockito.times(1)).forward(req, resp);
        String jsonDelete = "{\"action\" : \"delete\", \"id\" : \"1\"}";
        reader = new BufferedReader(new StringReader(jsonDelete));
        when(req.getReader()).thenReturn(reader);
        new UsersControllerServlet().doPost(req, resp);
        assertThat(validate.findAll().size(), is(0));
    }

    private String getJsonAddUser() {
        return new StringJoiner(",", "{", "}")
                .merge(new StringJoiner(":").add("\"action\"").add("\"add\""))
                .merge(new StringJoiner(":").add("\"name\"").add("\"Ivan\""))
                .merge(new StringJoiner(":").add("\"login\"").add("\"login\""))
                .merge(new StringJoiner(":").add("\"password\"").add("\"123\""))
                .merge(new StringJoiner(":").add("\"email\"").add("\"123@gmail.com\""))
                .merge(new StringJoiner(":").add("\"role\"").add("\"USER\""))
                .merge(new StringJoiner(":").add("\"country\"").add("\"USA\""))
                .merge(new StringJoiner(":").add("\"city\"").add("\"New-York\"")).toString();
    }

    private String getJsonUpdateUser() {
        return  new StringJoiner(",", "{", "}")
                .merge(new StringJoiner(":").add("\"action\"").add("\"update\""))
                .merge(new StringJoiner(":").add("\"id\"").add("\"1\""))
                .merge(new StringJoiner(":").add("\"name\"").add("\"Ivan\""))
                .merge(new StringJoiner(":").add("\"login\"").add("\"newLogin\""))
                .merge(new StringJoiner(":").add("\"password\"").add("\"123\""))
                .merge(new StringJoiner(":").add("\"email\"").add("\"333@gmail.com\""))
                .merge(new StringJoiner(":").add("\"role\"").add("\"ADMIN\""))
                .merge(new StringJoiner(":").add("\"country\"").add("\"USA\""))
                .merge(new StringJoiner(":").add("\"city\"").add("\"New-York\"")).toString();
    }
}