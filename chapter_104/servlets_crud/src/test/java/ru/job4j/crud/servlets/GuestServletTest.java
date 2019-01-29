package ru.job4j.crud.servlets;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Guest Servlet Test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.12.18
 */
public class GuestServletTest {

    @Test
    public void whenDoGetAndSessionHasValue() throws ServletException, IOException {
        String path = "/WEB-INF/view/main.jsp";
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("123");
        new GuestServlet().doGet(req, resp);
        Mockito.verify(dispatcher).forward(req, resp);
        Mockito.verify(req, Mockito.times(1)).getSession();
    }

    @Test
    public void whenDoGetAndSessionHasNotValue() throws ServletException, IOException {
        String path = "/WEB-INF/view/enter.jsp";
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn(null);
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
        new GuestServlet().doGet(req, resp);
        Mockito.verify(dispatcher).forward(req, resp);
        Mockito.verify(req, Mockito.times(1)).getSession();
    }

    @Test
    public void whenDoPostThenGetForward() throws ServletException, IOException {
        String path = "/action/main";
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
        new GuestServlet().doPost(req, resp);
        Mockito.verify(dispatcher).forward(req, resp);
        Mockito.verify(req, Mockito.never()).getSession();
    }
}