package ru.job4j.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.services.ServiceItems;

import javax.servlet.http.*;
import java.io.*;
import java.util.function.Supplier;

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

    /**
     * Contains service.
     *
     */
    private final ServiceItems service = ServiceItems.INSTANCE;

    /**
     * Get Request processing.
     * @param req req from client
     * @param resp resp for client
     * @throws IOException if IO exception
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        writeResponse(service::getAllItems, resp);
    }

    /**
     * Post Request processing
     * @param req request from client
     * @param resp response for client
     * @throws IOException if IO exception
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        writeResponse(() -> service.addItem(req.getParameter("desc")), resp);
    }

    /**
     * Write response
     *
     * @param supplier supplier function
     * @param response HttpServletResponse
     * @param <T> type parameter
     */
    private <T> void writeResponse(Supplier<T> supplier, HttpServletResponse response) throws IOException {
        try {
            PrintWriter writer = response.getWriter();
            new ObjectMapper().writeValue(writer, supplier.get());
            writer.flush();
        } catch (Exception ex) {
            LOG.error("Exception controller", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}