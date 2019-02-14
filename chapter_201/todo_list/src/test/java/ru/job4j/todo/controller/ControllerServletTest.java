package ru.job4j.todo.controller;

import org.junit.Test;

import javax.servlet.http.*;
import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Controller servlet test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-13
 */
public class ControllerServletTest {

    @Test
    public void whenGetAllItemsFromDoGet() throws IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var writer = new StringWriter();
        when(resp.getWriter()).thenReturn(new PrintWriter(writer));
        new ControllerServlet().doGet(req, resp);
    }

    @Test
    public void whenWantAddItemThenDoPost() throws IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var writer = new StringWriter();
        when(resp.getWriter()).thenReturn(new PrintWriter(writer));
        when(req.getParameter("desc")).thenReturn("desc123");
        new ControllerServlet().doPost(req, resp);
        var rst = writer.toString();
        assertTrue(rst.contains("desc123"));
    }
}