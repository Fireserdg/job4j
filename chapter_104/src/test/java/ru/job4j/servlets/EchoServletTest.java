package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * EchoServlet test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.12.18
 */
public class EchoServletTest {

    @Test
    public void whenDoGetThenResult() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ServletOutputStream outputStream = mock(ServletOutputStream.class);
        when(resp.getOutputStream()).thenReturn(outputStream);
        new EchoServlet().doGet(req, resp);
    }
}