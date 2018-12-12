package ru.job4j.crud.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.Validate;
import ru.job4j.crud.ValidateService;
import ru.job4j.crud.ValidateStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * UsersServlet test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.12.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UsersServletTest {

    @Test
    public void whenDoPostThenDoGetAndGetRequestDispatcher() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        String path = "/WEB-INF/view/list.jsp";
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
        new UsersServlet().doPost(req, resp);
        Mockito.verify(dispatcher, times(1)).forward(req, resp);
        Mockito.verify(req, never()).getSession();
    }
}