package ru.job4j.todo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.models.Item;
import ru.job4j.todo.services.ServiceItems;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;

/**
 * Controller
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-12
 */

public class ControllerServlet extends HttpServlet {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ControllerServlet.class);

    private final ServiceItems service = ServiceItems.INSTANCE;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("json");
        List<Item> items = service.getAllItems();
        PrintWriter writer = response.getWriter();
        new ObjectMapper().writeValue(writer, items);
        writer.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String desc = request.getParameter("desc");
        Item item = service.addItem(desc);
        PrintWriter writer = response.getWriter();
        new ObjectMapper().writeValue(writer, item);
        writer.flush();
    }

    @Override
    public void destroy() {
        service.close();
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
        LOG.info("Servlet destroy");
    }
}
