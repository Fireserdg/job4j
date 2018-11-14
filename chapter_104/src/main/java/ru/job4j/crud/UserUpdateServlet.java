package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User Update
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.11.18
 */
public class UserUpdateServlet extends HttpServlet {

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
        User user = this.validate.findById(req.getParameter("id"));
        writer.append("<!DOCTYPE html>")
                .append("<html lang='en'>")
                .append("<head>"
                        + "<meta charset='UTF-8'>"
                        + "<title>Update Users</title>"
                        + "</head>"
                        + "<body>"
                        + "<h2>Update User</h2>"
                        + "<form action='")
                .append(req.getContextPath())
                .append("/' method='POST'>"
                        + "<input type='hidden' name='action' value='update'/>"
                        + "<input type='hidden' name='id' value='")
                .append(user.getId())
                .append("'/>"
                        + "Please enter new name: <input type='text' "
                        + "name='name' placeholder='Your name=")
                .append(user.getName())
                .append("'/><br>"
                        + "Please enter new login: <input type='text' "
                        + " name='login' placeholder='Your login=")
                .append(user.getLogin())
                .append("'/><br>"
                        + "Please enter new email: <input type='text' "
                        + " name='email' placeholder='Your email=")
                .append(user.getEmail())
                .append("'/><br>"
                        + "<input type='submit' value='submit'/>"
                        + "</form><br>")
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