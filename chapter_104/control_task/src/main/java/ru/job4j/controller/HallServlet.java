package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.Accounts;
import ru.job4j.model.Halls;
import ru.job4j.service.HallService;
import ru.job4j.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("json");
        Service service = HallService.getInstance();
        List<Halls> halls = service.getHalls();
        PrintWriter writer = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(writer, halls);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String value;
        StringBuilder sb = new StringBuilder();
        while ((value = reader.readLine()) != null) {
            sb.append(value);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Accounts account = objectMapper.readValue(sb.toString(), Accounts.class);
        Service service = HallService.getInstance();
        service.addAccount(account);
    }

    @Override
    public void destroy() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver element = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(element);
                LOG.info("Deregister JDBC driver {}", element);
            } catch (SQLException e) {
                LOG.error("Error deregister JDBC driver {}", element, e, e.getMessage());
            }
        }
        LOG.info("Filter destroy");
    }
}
