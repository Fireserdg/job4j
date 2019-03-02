package ru.job4j.words;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.number.ServiceNumber;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Service test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-03-02
 */
public class ServiceDropWordTest {

    /**
     * Ref variable ServiceDropWord
     *
     */
    private ServiceDropWord service;

    /**
     * Init for test
     *
     */
    @Before
    public void init() {
        service = new ServiceDropWord();
    }

    @Test
    public void dropAbusesFromTextWhereHasAbuses() throws IOException {
        var stopWords = new String[]{"first", "five"};
        var str = "first, second, third, forth, five";
        try (var in = new ByteArrayInputStream(str.getBytes());
            var out = new ByteArrayOutputStream()) {
            service.dropAbuses(in, out, stopWords);
            assertThat(out.toString(), is("second, third, forth,"));
        }
    }

    @Test
    public void dropAbusesFromTextWhereHasNotAbuses() throws IOException {
        var stopWords = new String[]{"one", "two"};
        var str = "first, second, third, forth, five";
        try (var in = new ByteArrayInputStream(str.getBytes());
             var out = new ByteArrayOutputStream()) {
            service.dropAbuses(in, out, stopWords);
            assertThat(out.toString(), is(str));
        }
    }

    @Test
    public void dropAbusesFromFileWhereHasAbuses() throws IOException {
        var stopWords = new String[]{"skip", "valuable"};
        try (var in = ServiceNumber.class.getClassLoader()
                .getResourceAsStream("text_with_abuses.txt");
             var out = new ByteArrayOutputStream()) {
            service.dropAbuses(in, out, stopWords);
            assertThat(out.toString(), is(new StringJoiner(System.lineSeparator())
                    .add("Here's the next topic you need to learn.")
                    .add("You can it but you might lose information.")
                    .toString()));
        }
    }

    @Test
    public void dropAbusesFromFileWhereHasNotAbuses() throws IOException {
        var stopWords = new String[]{"Tik", "Tak"};
        try (var in = ServiceNumber.class.getClassLoader()
                .getResourceAsStream("text_not_abuses.txt");
             var out = new ByteArrayOutputStream()) {
            service.dropAbuses(in, out, stopWords);
            assertThat(out.toString(), is(new StringJoiner(System.lineSeparator())
                    .add("You are working on project")
                    .add("\"Tic-Tac-Toe with AI\"")
                    .toString()));
        }
    }

    @Test(expected = IOException.class)
    public void whenCloseStreamThenGetException() throws IOException {
        var stopWords = new String[]{"Tik", "Tak"};
        try (var in = ServiceNumber.class.getClassLoader()
                .getResourceAsStream("text_not_abuses.txt");
             var out = new ByteArrayOutputStream()) {
            Objects.requireNonNull(in).close();
            service.dropAbuses(in, out, stopWords);
        }
    }
}