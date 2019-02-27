package ru.job4j.number;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test service number.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-27
 */
public class ServiceNumberTest {

    /**
     * Service number
     *
     */
    private ServiceNumber service;

    /**
     * Init object for test
     *
     */
    @Before
    public void init() {
        service = new ServiceNumber();
    }

    @Test
    public void whenHasEvenNumberToStringThenGetResult() throws IOException {
        var str = "1 3 4 5";
        try (var in = new ByteArrayInputStream(str.getBytes())) {
            var result = service.isNumber(in);
            assertThat(result, is(true));
        }
    }

    @Test
    public void whenHasEvenNumberToFileThenReadAndGetResult() throws IOException {
        try (var stream = ServiceNumber.class
                .getClassLoader().getResourceAsStream("even.txt")) {
            var result = service.isNumber(stream);
            assertThat(result, is(true));
        }
    }

    @Test
    public void whenHasNotEvenNumberToFileThenReadAndGetResult() throws IOException {
        try (var stream = ServiceNumber.class
                .getClassLoader().getResourceAsStream("uneven.txt")) {
            boolean result = service.isNumber(stream);
            assertThat(result, is(false));
        }
    }

    @Test
    public void whenHasNotEvenNumberToStringThenGetResult() throws IOException {
        var str = "1 3 5";
        try (var in = new ByteArrayInputStream(str.getBytes())) {
            var result = service.isNumber(in);
            assertThat(result, is(false));
        }
    }
}