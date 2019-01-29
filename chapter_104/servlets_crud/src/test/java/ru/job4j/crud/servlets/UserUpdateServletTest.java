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
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * User Update Servlet test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.12.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserUpdateServletTest {

    @Test
    public void whenDoPostThenDoGetAndGetRequestDispatcher() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        String path = "/WEB-INF/view/update.jsp";
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
        String[] addUser = {"nameUpdate", "login", "password",
                "email", "USER", "Russia", "Moscow"};
        validate.add(addUser);
        List<User> list = validate.findAll();
        final User user = list.stream().filter(
                value -> value.getName().equals("nameUpdate")).findFirst().get();
        when(req.getParameter("id")).thenReturn(user.getId());
        new UserUpdateServlet().doPost(req, resp);
        Mockito.verify(dispatcher, times(
                1)).forward(req, resp);
        Mockito.verify(req, never()).getSession();
    }
}