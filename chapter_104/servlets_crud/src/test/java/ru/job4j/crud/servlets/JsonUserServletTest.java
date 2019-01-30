package ru.job4j.crud.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.Validate;
import ru.job4j.crud.ValidateService;
import ru.job4j.crud.ValidateStub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * JsonUserServlet test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 27.01.19
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
@PowerMockIgnore({"javax.net.ssl.*", "javax.security.*"})
public class JsonUserServletTest {

    @Test
    public void whenMethodGetOnServlet() throws IOException {
        Validate validate = new ValidateStub();
        mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(writer);
    }

    @Test
    public void whenMethodPostOnServlet() throws IOException {
        Validate validate = new ValidateStub();
        mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(writer);
        when(req.getParameter("country")).thenReturn("1");
        Mockito.verify(req, times(0)).getSession();
    }
}