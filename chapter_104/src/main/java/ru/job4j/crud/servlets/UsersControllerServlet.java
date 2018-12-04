package ru.job4j.crud.servlets;

import ru.job4j.crud.DispatchPattern;
import ru.job4j.crud.UserIdException;
import ru.job4j.crud.UserLoginException;
import ru.job4j.crud.ValidateService;
import ru.job4j.crud.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * UsersControllerServlet
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 02.11.18
 */
public class UsersControllerServlet extends HttpServlet {

    /**
     * Contains validate.
     */
    private final DispatchPattern dispatch = DispatchPattern.getInstance();

    /**
     * Get Request processing.
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = ValidateService.getInstance().findById(
                (String) session.getAttribute("id"));
        session.setAttribute("name", user.getName());
        session.setAttribute("role", user.getRole());
        req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
    }

    /**
     * Post Request processing
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        try {
            String msg = this.dispatch.init().sent(
                    () -> req.getParameterMap().values().stream()
                    .map(n -> n[0] == null ? "" : n[0].replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("&", "&amp;")).collect(Collectors.toList()));
            req.setAttribute("msg", msg);
        } catch (UserLoginException | UserIdException exc) {
            req.setAttribute("exc", exc.getMessage());
        }
        HttpSession session = req.getSession();
        if (req.getParameter("action").equals("delete")
                && req.getParameter("id").equals(session.getAttribute("id"))) {
            req.setAttribute("exit", "YES");
            session.invalidate();
        }
        req.getRequestDispatcher("/WEB-INF/view/answer.jsp").forward(req, resp);
    }
}