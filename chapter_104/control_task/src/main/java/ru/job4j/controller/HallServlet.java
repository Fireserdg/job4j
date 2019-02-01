package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.*;
import ru.job4j.model.*;
import ru.job4j.service.*;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Hall servlet for do action from cinema
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public class HallServlet extends HttpServlet {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HallServlet.class);

    /**
     * Contains service.
     */
    private static final Service SERVICE = HallService.getInstance();

    /**
     * Get Request processing.
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("json");
        Service service = HallService.getInstance();
        PrintWriter writer = resp.getWriter();
        new ObjectMapper().writeValue(writer, service.getHalls());
        writer.flush();
    }

    /**
     * Post Request processing
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("action") != null) {
            Hall hall = SERVICE.getHallsById(
                    Integer.parseInt((String) session.getAttribute("id")));
            new ObjectMapper().writeValue(writer, hall);
            session.invalidate();
            writer.flush();
        } else if (req.getParameter("id") != null) {
            String id = req.getParameter("id");
            session.setAttribute("id", id);
            writer.append(Service.MESSAGE);
            writer.flush();
        } else {
            BufferedReader reader = req.getReader();
            Accounts accounts = new ObjectMapper().readValue(reader.readLine(), Accounts.class);
            writer.append(SERVICE.addAccount(accounts));
            reader.close();
            writer.flush();
        }
    }

    /**
     * Destroy servlet
     */
    @Override
    public void destroy() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver element = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(element);
                LOG.info("Deregister JDBC driver {}", element.getClass().getSimpleName());
            } catch (SQLException e) {
                LOG.error("Error deregister JDBC driver {}", element, e, e.getMessage());
            }
        }
        LOG.info("Servlet destroy");
    }
}