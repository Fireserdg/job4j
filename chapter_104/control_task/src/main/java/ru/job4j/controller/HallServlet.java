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
        resp.setCharacterEncoding("UtF-8");
        HttpSession session = req.getSession();
        if (req.getParameter("action") != null) {
            Hall hall = SERVICE.getHallsById(
                    Integer.parseInt((String) session.getAttribute("id")));
            PrintWriter pw = resp.getWriter();
            new ObjectMapper().writeValue(pw, hall);
            session.invalidate();
            pw.flush();
        } else if (req.getParameter("id") != null) {
            String id = req.getParameter("id");
            session.setAttribute("id", id);
            LOG.info("id=" + id);
        } else {
            BufferedReader reader = req.getReader();
            String s = reader.readLine();
            reader.close();
            Accounts accounts = new ObjectMapper().readValue(s, Accounts.class);
            String s1 = SERVICE.addAccount(accounts);
            PrintWriter pr = resp.getWriter();
            pr.append(s1).flush();
            //Решить с сессией или переехать в фильтр или что то типа Диспатч паттерна сделать.
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
