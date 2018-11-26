package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
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
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UsersControllerServlet.class);

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
        req.getRequestDispatcher("/WEB-INF/view/users.jsp").forward(req, resp);
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
        } catch (UserNotFoundException exc) {
            req.setAttribute("msg", exc.getMessage());
        }
        req.getRequestDispatcher("/WEB-INF/view/answer.jsp").forward(req, resp);
    }

    /**
     * Destroy servlet.
     *
     */
    @Override
    public void destroy() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                LOG.info("Deregistering JDBC driver {}", driver);
            } catch (SQLException e) {
                LOG.error("Error deregistering JDBC driver {}", driver, e, e.getMessage());
            }
        }
    }
}