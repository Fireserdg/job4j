package ru.job4j.crud.servlets;

import org.junit.Test;
import org.mockito.Mockito;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * SigningController test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.12.18
 */
public class SigningControllerServletTest {

    @Test
    public void whenDoGetThenRequestDispatcher() throws ServletException, IOException {
        String path = "/WEB-INF/view/login.jsp";
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
        new SigningControllerServlet().doGet(req, resp);
        Mockito.verify(dispatcher, Mockito.times(1)).forward(req, resp);
    }

    @Test
    public void whenDoPostCheckLoginAndPassword() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("login")).thenReturn("333");
        when(req.getParameter("password")).thenReturn("111");
        String path = "/WEB-INF/view/login.jsp";
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
        new SigningControllerServlet().doPost(req, resp);
        verify(dispatcher, times(1)).forward(req, resp);
    }
}