package ru.job4j.crud.servlets;

import ru.job4j.crud.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Users servlet
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.11.18
 */
public class UsersServlet extends HttpServlet {

    /**
     * Get Request processing.
     * @param req request from client.
     * @param resp response for client.
     * @throws ServletException Servlet Exception
     * @throws IOException IO exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("users", ValidateService.getInstance().findAll());
        req.getRequestDispatcher("/WEB-INF/view/list.jsp").forward(req, resp);
    }

    /**
     * Post Request processing
     * @param req request from client.
     * @param resp response for client.
     * @throws ServletException Servlet Exception
     * @throws IOException IO exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}