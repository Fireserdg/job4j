package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

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
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>Main page</title>"
                + "</head>"
                + "<body>"
                + "<h2><center>Hello! Selected action.</center></h2>"
                + "<center><form action='")
                .append(req.getContextPath())
                .append("/create' method='GET'>"
                        + "Go to page for create new user "
                        + "<input type='submit' value='Go to page'/>"
                        + "</form></center></p>"
                        + "<p><center><form action='")
                .append(req.getContextPath())
                .append("/list' method='GET'>"
                        + "Go to the page with the list of users "
                        + "<input type='submit' value='Go to page'/>"
                        + "</form></center>"
                        + "</body>"
                        + "</html>");
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
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>Main page</title>"
                + "</head>"
                + "<body><p><center>");
        try {
            String msg = new DispatchPattern(this.validate).init().sent(
                    () -> req.getParameterMap().values().stream()
                    .map(n -> n[0] == null ? "" : n[0].replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("&", "&amp;")).collect(Collectors.toList()));
            writer.append(msg);
        } catch (IllegalArgumentException exc) {
            writer.append(exc.getMessage());
        }
        writer.append("</center></p><form action='")
                .append(req.getContextPath())
                .append("/' method='GET'>"
                        + "<input type='submit' value='Back to main page'/>"
                        + "</form>"
                        + "</body>"
                        + "</html>").flush();
    }
}