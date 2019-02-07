package ru.job4j.json;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringJoiner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Test for Json servlet
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 17.12.18
 */
public class JsonServletTest {

    @Test
    public void whenDoGetThenResult() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        final PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
        new JsonServlet().doGet(request, response);
        verify(request, never()).getSession();
        verify(request, never()).getMethod();
    }

    @Test
    public void whenDoPostThenResult() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        final PrintWriter writer = mock(PrintWriter.class);
        final BufferedReader reader = mock(BufferedReader.class);
        StringJoiner json = new StringJoiner(",", "{", "}")
                .merge(new StringJoiner(":").add("\"firstName\"").add("\"first\""))
                .merge(new StringJoiner(":").add("\"lastName\"").add("\"last\""))
                .merge(new StringJoiner(":").add("\"sex\"").add("\"Male\""))
                .merge(new StringJoiner(":").add("\"desc\"").add("\"description\""));
        when(request.getReader()).thenReturn(reader);
        when(reader.ready()).thenReturn(true).thenReturn(false);
        when(reader.readLine()).thenReturn(json.toString());
        when(response.getWriter()).thenReturn(writer);
        new JsonServlet().doPost(request, response);
    }
}