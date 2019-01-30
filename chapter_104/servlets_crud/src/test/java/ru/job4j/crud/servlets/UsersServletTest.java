package ru.job4j.crud.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.Validate;
import ru.job4j.crud.ValidateService;
import ru.job4j.crud.ValidateStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * UsersServlet test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.12.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
@PowerMockIgnore({"javax.net.ssl.*", "javax.security.*"})
public class UsersServletTest {

    @Test
    public void whenDoPostThenDoGetAndGetRequestDispatcher() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        String path = "/WEB-INF/view/list.jsp";
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn("1");
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
        new UsersServlet().doPost(req, resp);
        verify(dispatcher, times(1)).forward(req, resp);
        verify(req, times(1)).getSession();
    }
}