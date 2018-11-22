package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
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
        StringBuilder sb = new StringBuilder();
        try {
            String msg = new DispatchPattern(this.validate).init().sent(
                    () -> req.getParameterMap().values().stream()
                    .map(n -> n[0] == null ? "" : n[0].replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("&", "&amp;")).collect(Collectors.toList()));
            sb.append(msg);
        } catch (IllegalArgumentException exc) {
            sb.append(exc.getMessage());
        }
        resp.sendRedirect(String.format("%s/pages/answer.jsp?msg=%s", req.getContextPath(),
                URLEncoder.encode(sb.toString(), "UTF-8")));
    }
}