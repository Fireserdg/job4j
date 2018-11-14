package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Users servlet
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.11.18
 */
public class UsersServlet extends HttpServlet {

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
        StringBuilder sb = new StringBuilder("<table width='60%' border='1'"
                + " cellpadding='4' cellspacing='0' bgcolor='#FFF8DC'>");
        sb.append("<caption>Current list of users</caption>"
                + "<tr><th>Id</th><th>User name</th><th>Create</th>"
                + "<th>Actions</th></tr>");
        if (validate.findAll().isEmpty()) {
            sb.append("<tr style='background: #FFFAFA; "
                    + "color: #C0C0C0; font-style: italic;'>"
                    + "<th>Empty</th><th>Empty</th><th>Empty</th>"
                    + "<th>Empty</th></tr>");
        } else {
            validate.findAll().forEach((value) ->
                    sb.append("<tr><td align='center'>")
                            .append(value.getId())
                            .append("</td>"
                                    + "<td align='center'>")
                            .append(value.getName())
                            .append("</td>"
                                    + "<td align='center'>")
                            .append(LocalDateTime.ofInstant(
                                    Instant.ofEpochMilli(value.getCreateDate()),
                                    TimeZone.getDefault().toZoneId()).format(
                                    DateTimeFormatter.ofPattern("yyyy-MMM-dd, HH:mm")
                                            .withLocale(new Locale("en"))))
                            .append("</td>")
                            .append("<td align='center'>")
                            .append("<form action='")
                            .append(req.getContextPath())
                            .append("/edit' method='GET'>"
                                    + "<input type='hidden' name='id' value='")
                            .append(value.getId())
                            .append("'/>"
                                    + "<input type='submit' class='sub' value='Update'/>"
                                    + "</form>")
                            .append("<form action='")
                            .append(req.getContextPath())
                            .append("/' method='POST'>"
                                    + "<input type='hidden' name='action' value='delete'/>"
                                    + "<input type='hidden' name='id' value='")
                            .append(value.getId())
                            .append("'/>"
                                    + "<input type='submit' class='sub' value='Delete'/>"
                                    + "</form>"
                                    + "</td></tr>"));
        }
            sb.append("</table>");
        writer.append("<!DOCTYPE html>")
                .append("<html lang='en'>")
                .append("<head>"
                        + "<meta charset='UTF-8'>"
                        + "<title>List of Users</title>"
                        + "<style> .sub {background: #FFF5EE; "
                        + "color: #696969; font-size: 9pt; "
                        + "font-weight: 600; border-radius: 10px; "
                        + "padding: 5px 10px; margin: 0px;} "
                        + "</style>"
                        + "</head>"
                        + "<body>")
                .append(sb.toString())
                .append("<p><form action='")
                .append(req.getContextPath())
                .append("/' method='GET'>")
                .append("<input type='submit' value='Back to main page'/>"
                        + "</form></p>")
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