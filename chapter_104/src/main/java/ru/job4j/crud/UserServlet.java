package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * UserServlet
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 02.11.18
 */
public class UserServlet extends HttpServlet {

    /**
     * Contains validate.
     */
    private final ValidateService validate = ValidateService.getInstance();

    /**
     * Get Request processing.
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        resp.getWriter().append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<body>")
                .append("<p><center>List of users</center></p>");
        if (validate.findAll().isEmpty()) {
            writer.append("<p><center>No Users</center></p>");
        } else {
            validate.findAll().forEach((value) -> writer.append("<p><center>")
                    .append(value.toString()).append("</center></p>"));
        }
        writer.append("</body>")
                .append("</html>").flush();
    }

    /**
     * Post Request processing
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String> params = new ArrayList<>();
        Enumeration<String> en = req.getParameterNames();
        while (en.hasMoreElements()) {
            params.add(req.getParameter(en.nextElement()));
        }
        String answer = new DispatchPattern(
                this.validate).init().sent(() -> params);
        resp.setContentType("text/html");
        resp.getWriter().append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<body>")
                .append("<p><center>")
                .append(answer)
                .append("</center></p>")
                .append("</body>")
                .append("</html>").flush();
    }
}