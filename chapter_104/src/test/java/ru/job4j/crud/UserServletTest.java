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

    @Test
    public void whenOperationByUsersThenGetResult() throws InterruptedException {
        ValidateService val = ValidateService.getInstance();
        for (int i = 1; i < 200; i++) {
            int count = i;
            new Thread(() -> new DispatchPattern(val).
                        init().sent(() -> Arrays.asList(
                                "add", String.format("User%s", count)))
            ).start();
        }
        Thread.sleep(500);
        int resultSize = val.findAll().size();
        int expectedSize = 199;
        for (int i = 5; i < 15; i++) {
            int countUp = i;
            int countDel = i + 10;
            new Thread(() -> new DispatchPattern(val).
                    init().sent(() -> Arrays.asList(
                    "update", String.valueOf(countUp), String.format(
                            "UpdateUser%s", countUp)))
            ).start();
            new Thread(() -> new DispatchPattern(val).
                    init().sent(() -> Arrays.asList(
                    "delete", String.valueOf(countDel)))
            ).start();

        }
        Thread.sleep(500);
        System.out.println(val.findAll());
        assertThat(resultSize, is(expectedSize));
        assertThat(val.findById("99").getName(), is("User99"));
        assertThat(val.findById("5").getName(), is("UpdateUser5"));
        assertThat(val.findById("14").getName(), is("UpdateUser14"));
        assertNull(val.findById("15"));
        assertNull(val.findById("24"));
        assertThat(val.findAll().size(), is(189));
        assertThat(new DispatchPattern(val)
                        .init().sent(() -> Arrays.asList("add", "Jon")),
                is(String.format(Message.MSG_ADD, "Jon")));
        assertThat(new DispatchPattern(val)
                        .init().sent(() -> Arrays.asList("update", "1", "Bill")),
                is(Message.MSG_UPDATE));
        assertThat(new DispatchPattern(val)
                        .init().sent(() -> Arrays.asList("update", "15", "Bill")),
                is(String.format(Message.MSG_NOT_EXIST, "15")));
        assertThat(new DispatchPattern(val)
                        .init().sent(() -> Arrays.asList("delete", "2")),
                is(Message.MSG_DELETE));
        assertThat(new DispatchPattern(val)
                        .init().sent(() -> Arrays.asList("delete", "20")),
                is(String.format(Message.MSG_NOT_EXIST, "20")));
    }
}