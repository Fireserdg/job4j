package ru.job4j.crud.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for logout session.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 01.12.18
 */
public class LogoutSessionServlet extends HttpServlet {

    /**
     * Get Request processing.
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    /**
     * Post Request processing
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}