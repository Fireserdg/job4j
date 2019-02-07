package ru.job4j.controller;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.config.Config;

import javax.servlet.http.*;

import java.io.*;
import java.sql.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Hall Servlet test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 01.02.19
 */
public class HallServletTest {

    /**
     * Request for mock.
     */
    private HttpServletRequest request;

    /**
     * Response for mock.
     */
    private HttpServletResponse response;

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HallServletTest.class);

    @Before
    public void createMockObject() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void whenRequestAboutAllHallsThenGetResponse() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter writer = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        new HallServlet().doGet(request, response);
        assertThat(writer.toString().contains("booked"), is(true));
        assertThat(writer.toString().contains("price"), is(true));
    }

    @Test
    public void whenSentRequestWithHallIdThenPutIdSession() throws IOException {
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("22");
        StringWriter writer = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        new HallServlet().doPost(request, response);
        verify(session, times(1)).setAttribute("id", "22");
    }

    @Test
    public void whenSendRequestForGetHallByIdThenGetHall() throws IOException {
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("action")).thenReturn("someAction");
        when(session.getAttribute("id")).thenReturn("11");
        StringWriter writer = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        new HallServlet().doPost(request, response);
        String result = writer.toString();
        String expected = "{\"id\":11,\"row\":1,\"seat\":1,\"price\":500,\"booked\":false}";
        assertThat(result, is(expected));
    }

    @Test
    public void whenSendJsonRequestForBookedHallThenGetMessage() throws IOException, SQLException {
        String json = "{\"id\":\"22\",\"username\":\"Ivanov Ivan Ivanovich\",\"phone\":\"+7(123)4443322\"}";
        StringReader reader = new StringReader(json);
        StringWriter writer = new StringWriter();
        when(request.getReader()).thenReturn(new BufferedReader(reader));
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        new HallServlet().doPost(request, response);
        String expected = "Билет успешно приобретен";
        String result = writer.toString();
        assertThat(result, is(expected));
        initConnection("22");
    }


    @Test
    public void whenTwoUsersWantBuySameHallAndSameTimeButBuyOneUser() throws IOException, InterruptedException, SQLException {
        String firstUser = "{\"id\":\"33\",\"username\":\"Petrov Alex Ivanovich\","
                + "\"phone\":\"+7(123)4443311\"}";
        new Thread(() -> {
            try {
                Thread.sleep(100);
                String secondUser = "{\"id\":\"33\",\"username\":\"Kozlov Fedor Vasilevich\","
                        + "\"phone\":\"+7(321)4443322\"}";
                StringReader reader = new StringReader(secondUser);
                StringWriter writer = new StringWriter();
                when(request.getReader()).thenReturn(new BufferedReader(reader));
                when(response.getWriter()).thenReturn(new PrintWriter(writer));
                new HallServlet().doPost(request, response);
                String expected = "Билет уже купили";
                String result = writer.toString();
                assertThat(result, is(expected));
            } catch (InterruptedException | IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }).start();
        StringReader reader = new StringReader(firstUser);
        StringWriter writer = new StringWriter();
        when(request.getReader()).thenReturn(new BufferedReader(reader));
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        new HallServlet().doPost(request, response);
        String expected = "Билет успешно приобретен";
        String result = writer.toString();
        assertThat(result, is(expected));
        Thread.sleep(500);
        initConnection("33");
    }

    public void initConnection(String id) throws SQLException {
        Config config = Config.getInstance();
        String url = config.getValue("db.url");
        String name = config.getValue("db.name");
        String password = config.getValue("db.password");
        try (Connection conn = DriverManager.getConnection(url, name, password);
             Statement st = conn.createStatement()) {
            st.addBatch("DELETE from accounts WHERE id=" + id);
            st.addBatch("UPDATE hall SET booked=false WHERE id =" + id);
            st.executeBatch();
        }
    }
}