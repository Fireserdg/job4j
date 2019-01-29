package ru.job4j.crud.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * User create Servlet test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.12.18
 */
public class UserCreateServletTest {

    @Test
    public void whenDoPostThenDoGetAndGetRequestDispatcher() throws ServletException, IOException {
        String path = "/WEB-INF/view/create.jsp";
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("login");
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
        new UserCreateServlet().doPost(req, resp);
        verify(dispatcher, times(1)).forward(req, resp);
        verify(req, times(1)).getSession();
    }
}