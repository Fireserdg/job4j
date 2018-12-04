package ru.job4j.crud.servlets;

import ru.job4j.crud.models.Role;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import static java.util.Objects.isNull;

/**
 * Servlet for create user.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.11.18
 */
public class UserCreateServlet extends HttpServlet {

    /**
     * Get Request processing.
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        if (isNull(session.getAttribute("login"))) {
            session.setAttribute("role", Role.GUEST);
        }
        req.getRequestDispatcher("/WEB-INF/view/create.jsp").forward(req, resp);
    }

    /**
     * Post Request processing
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}