package ru.job4j.crud;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * AuthFilter test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.12.18
 */
public class AuthFilterTest {

    @Test
    public void whenDoFilterThenGetResult() throws IOException, ServletException {
        String path = "/WEB-INF/view/login.jsp";
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn(null);
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(req.getRequestURI()).thenReturn("/acton/update");
        new AuthFilter().doFilter(req, resp, filterChain);
        verify(req, times(1)).getSession();
        verify(session, times(1)).getAttribute("login");
        when(session.getAttribute("login")).thenReturn("login444");
        new AuthFilter().doFilter(req, resp, filterChain);
        verify(filterChain, times(1)).doFilter(req, resp);
    }
}