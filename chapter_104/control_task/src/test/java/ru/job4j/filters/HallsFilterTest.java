package ru.job4j.filters;

import org.junit.Test;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Filter test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 05.02.19
 */
public class HallsFilterTest {

    @Test
    public void whenTheRequestAndResponsePassThroughTheFilter() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("http//localhost:8080/hall");
        new HallsFilter().doFilter(request, response, chain);
        verify(request, times(1)).setCharacterEncoding("UTF-8");
        verify(response, times(1)).setCharacterEncoding("UTF-8");
        verify(response, times(1)).setContentType("json");
    }
}