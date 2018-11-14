package ru.job4j.crud;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * Test when working with multithreading
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 10.11.18
 */
public class UserServletTest {

    @Test (expected = UserNotFoundException.class)
    public void whenOperationByUsersThenGetResult() throws InterruptedException {
        ValidateService val = ValidateService.getInstance();
        for (int i = 1; i < 300; i++) {
            int count = i;
            new Thread(() -> new DispatchPattern(val).
                        init().sent(() -> Arrays.asList("add",
                    String.format("User%s", count),
                    String.format("login.%s", count),
                    String.format("email%s@.gmail.com", count)))
            ).start();
        }
        Thread.sleep(500);
        int resultSize = val.findAll().size();
        int expectedSize = 299;
        for (int i = 5; i < 30; i++) {
            int countUp = i;
            int countDel = i + 25;
            new Thread(() -> new DispatchPattern(val).
                    init().sent(() -> Arrays.asList("update",
                    String.valueOf(countUp),
                    String.format("UpdateUser%s", countUp),
                    String.format("Updatelogin%s", countUp),
                    String.format("UpdateEmail%s", countUp)))
            ).start();
            new Thread(() -> new DispatchPattern(val).
                    init().sent(() -> Arrays.asList(
                    "delete", String.valueOf(countDel)))
            ).start();

        }
        Thread.sleep(500);
        val.findAll().forEach(System.out::println);
        assertThat(resultSize, is(expectedSize));
        assertThat(val.findById("99").getName(), is("User99"));
        assertThat(val.findById("5").getName(), is("UpdateUser5"));
        assertThat(val.findById("14").getName(), is("UpdateUser14"));
        assertNull(val.findById("30"));
        assertNull(val.findById("49"));
        assertThat(val.findAll().size(), is(274));
        assertThat(new DispatchPattern(val)
                        .init().sent(() -> Arrays.asList("add", "Jon", "login", "email")),
                is(String.format(Message.MSG_ADD, "Jon")));
        assertThat(new DispatchPattern(val)
                        .init().sent(() -> Arrays.asList("update", "1", "Bill", "", "login")),
                is(String.format(Message.MSG_UPDATE, "1")));
        assertThat(new DispatchPattern(val)
                        .init().sent(() -> Arrays.asList("delete", "2")),
                is(String.format(Message.MSG_DELETE, "2")));
        new DispatchPattern(val)
                        .init().sent(() -> Arrays.asList("delete", "37"));
    }
}