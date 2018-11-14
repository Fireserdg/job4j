package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Servlet for create user.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.11.18
 */
public class UserCreateServlet extends HttpServlet {

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
        writer.append("<!DOCTYPE html>")
                .append("<html lang='en'>")
                .append("<head>"
                        + "<meta charset='UTF-8'>"
                        + "<title>Create User</title>"
                        + "</head>"
                        + "<body>")
                .append("<h2>Create User</h2>")
                .append("<form action='")
                .append(req.getContextPath())
                .append("/' method='POST'>"
                        + "<input type='hidden' name='action' value='add'/>"
                        + "User name: <input type='text' name='name' required/><br>"
                        + "User login: <input type='text' name='login' required/><br>"
                        + "User email: <input type='text' name='email' required/><br>"
                        + "<input type='submit' value='Submit'/>"
                        + "</form>")
                .append("<form action='")
                .append(req.getContextPath())
                .append("/' method='GET'>")
                .append("<input type='submit' value='Back to main page'/>"
                        + "</form>")
                .append("</body>"
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
        doGet(req, resp);
    }
}