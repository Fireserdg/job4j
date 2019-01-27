package ru.job4j.crud.servlets;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import ru.job4j.crud.DispatchPattern;
import ru.job4j.crud.UserIdException;
import ru.job4j.crud.UserLoginException;
import ru.job4j.crud.ValidateService;
import ru.job4j.crud.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UsersControllerServlet
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 02.11.18
 */
public class UsersControllerServlet extends HttpServlet {

    /**
     * Contains validate.
     */
    private final DispatchPattern dispatch = DispatchPattern.getInstance();

    /**
     * Get Request processing.
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        try {
            User user = ValidateService.getInstance().findById(
                    (String) session.getAttribute("id"));
            session.setAttribute("name", user.getName());
            session.setAttribute("role", user.getRole());
            req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
        } catch (UserIdException ex) {
            req.getRequestDispatcher("/WEB-INF/view/enter.jsp").forward(req, resp);
        }

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
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String rst;
        while ((rst = reader.readLine()) != null) {
            sb.append(rst);
        }
        reader.close();
        PrintWriter printWriter = resp.getWriter();
        printWriter.append(getMessage(getParameter(sb.toString())));
        printWriter.flush();
    }

    /**
     * Get parameter from request
     * @param param parameter
     * @return list values
     * @throws IOException exception
     */
    private List<String> getParameter(String param) throws IOException {
        JsonParser parser  = new JsonFactory().createParser(param);
        List<String> list = new ArrayList<>();
        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();
            if (JsonToken.VALUE_STRING.equals(jsonToken)) {
                String value = parser.getValueAsString();
                list.add(value);
            }
        }
        return list;
    }

    /**
     * Get message about operation
     *
     * @param values values request parameters
     * @return message
     */
    private String getMessage(List<String> values) {
        String msg;
        try {
            msg = this.dispatch.init().sent(() -> values.stream()
                    .map(v -> v.replace(
                    "<", "&lt;").
                    replace(">", "&gt;").
                    replace("&", "&amp;"))
                    .collect(Collectors.toList()));
        } catch (UserLoginException | UserIdException ex) {
            msg = ex.getMessage();
        }
        return msg;
    }
}