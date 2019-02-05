package ru.job4j.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.controller.HallServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Filter for controller.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 05.02.19
 */

public class HallsFilter implements Filter {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HallServlet.class);


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
                && request.getRequestURI().contains("/hall")) {
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
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver element = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(element);
                LOG.info("Deregister JDBC driver {}", element.getClass());
            } catch (SQLException e) {
                LOG.error("Error deregister JDBC driver {}", element, e, e.getMessage());
            }
        }
        LOG.info("Filter destroy");
    }
}