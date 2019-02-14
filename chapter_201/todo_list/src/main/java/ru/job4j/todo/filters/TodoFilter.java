package ru.job4j.todo.filters;

import org.hibernate.HibernateException;
import org.slf4j.*;
import ru.job4j.todo.services.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.Enumeration;

/**
 * Filter
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-14
 */

public class TodoFilter implements Filter {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TodoFilter.class);


    /**
     * Do filter for processing request an response.
     *
     * @param req request.
     * @param resp response
     * @param chain filter chain.
     * @throws ServletException if exception.
     * @throws IOException if exception.
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getMethod().equals("GET")
                && request.getRequestURI().contains("/items")) {
            response.setContentType("json");
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }


    /**
     * Filter destroy
     *
     */
    public void destroy() {
        closeSessionFactory();
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver element = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(element);
                LOG.info("Deregister JDBC driver {}", element.getClass());
            } catch (SQLException e) {
                LOG.error("Error deregistering JDBC driver {}", element, e, e.getMessage());
            }
        }
        LOG.info("Filter destroy");
    }

    /**
     * Close session factory.
     *
     */
    private void closeSessionFactory() {
        try {
            if (SessionUtil.getFactory() != null && !SessionUtil.getFactory().isClosed()) {
                SessionUtil.getFactory().close();
                LOG.info("SessionFactory close");
            }
        } catch (HibernateException he) {
            LOG.error("Error close SessionFactory", he);
        }
    }

}
