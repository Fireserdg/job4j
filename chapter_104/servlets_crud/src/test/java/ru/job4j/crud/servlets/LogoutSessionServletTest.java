package ru.job4j.crud.servlets;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Logout Servlet Test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.12.18
 */
public class LogoutSessionServletTest {

    @Test
    public void whenDoGetAndGetSessionInvalidate() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        new LogoutSessionServlet().doGet(req, resp);
        verify(resp, Mockito.times(
                1)).sendRedirect(String.format("%s/", req.getContextPath()));
        verify(session, Mockito.times(1)).invalidate();

    }

    @Test
    public void whenDoPostThenDoGet() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        new LogoutSessionServlet().doPost(req, resp);
        verify(session, Mockito.times(1)).invalidate();
    }

}