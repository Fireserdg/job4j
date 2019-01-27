package ru.job4j.crud.servlets;

import ru.job4j.crud.models.User;
import ru.job4j.crud.ValidateService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * Signing controller
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 26.11.18
 */
public class SigningControllerServlet extends HttpServlet {

    /**
     * Get Request processing.
     * @param req request from client.
     * @param resp response for client.
     * @throws ServletException Servlet Exception
     * @throws IOException IO exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
    }

    /**
     * Post Request processing
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> user = ValidateService.getInstance().isCredentials(login, password);
        PrintWriter writer = resp.getWriter();
        if (user.isPresent()) {
            HttpSession session = req.getSession();
                session.setAttribute("id", user.get().getId());
                session.setAttribute("login", login);
            writer.append("Success");
            writer.flush();
        } else {
            writer.append("Credentials Invalid");
            writer.flush();
        }
    }
}