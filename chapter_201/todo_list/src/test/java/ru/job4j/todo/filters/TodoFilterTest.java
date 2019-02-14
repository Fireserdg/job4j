package ru.job4j.todo.filters;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import static org.mockito.Mockito.*;

/**
 * Filter test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-14
 */
public class TodoFilterTest {

    @Test
    public void whenFilterRequestMethodGetAndResponse() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/items");
        new TodoFilter().doFilter(request, response, chain);
        verify(request, times(1)).setCharacterEncoding("UTF-8");
        verify(response, times(1)).setCharacterEncoding("UTF-8");
        verify(response, times(1)).setContentType("json");
    }

    @Test
    public void whenFilterRequestMethodPostAndResponse() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        when(request.getMethod()).thenReturn("POST");
        new TodoFilter().doFilter(request, response, chain);
        verify(request, times(1)).setCharacterEncoding("UTF-8");
        verify(response, times(1)).setCharacterEncoding("UTF-8");
        verify(response, never()).setContentType("json");
        verify(request, never()).getRequestURI();
    }
}