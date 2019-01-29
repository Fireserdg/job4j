package ru.job4j.json;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Json Servlet
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 14.12.18
 */
public class JsonServlet extends HttpServlet {

    /**
     * Container for users
     *
     */
    private final Map<String, Person> storage = new ConcurrentHashMap<>();

    /**
     * Counter
     *
     */
    private final AtomicInteger count = new AtomicInteger(1);


    /**
     * Get Request processing
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append(new ObjectMapper().writeValueAsString(this.storage));
        writer.flush();
    }

    /**
     * Post Request processing.
     * @param req request from client.
     * @param resp response for client.
     * @throws IOException IO exception.
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        reader.close();
        Person person = new ObjectMapper().readValue(sb.toString(), Person.class);
        this.storage.computeIfAbsent(String.valueOf(count.getAndIncrement()), k -> {
            person.setId(k);
            return person;
        });
        PrintWriter writer = resp.getWriter();
        writer.append(new ObjectMapper().writeValueAsString(person));
        writer.flush();
    }
}