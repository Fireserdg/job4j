package ru.job4j.crud.servlets;

import static java.util.Objects.nonNull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Guest Servlet
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 03.12.18
 */
public class GuestServlet extends HttpServlet {

    /**
     * Get Request processing.
     * @param req request from client.
     * @param resp response for client.
     * @throws ServletException Servlet Exception
     * @throws IOException IO exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (nonNull(req.getSession().getAttribute("login"))) {
            req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/view/enter.jsp").forward(req, resp);
        }
    }

    /**
     * Post Request processing
     * @param req request from client.
     * @param resp response for client.
     * @throws ServletException Servlet Exception
     * @throws IOException IO exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/action/main").forward(req, resp);
    }
}