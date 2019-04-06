package ru.job4j.analize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Server operation analysis
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-04-06
 */
public class Analise {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Analise.class);

    /**
     * List duration
     *
     */
    private final List<String> duration = new ArrayList<>();

    /**
     * Status handler
     *
     */
    private final StatusHandler statusHandler = new StatusHandler().load(duration);

    /**
     * Unavailable for files
     *
     * @param source source file
     * @param target target file
     */
    public void unavailable(String source, String target) {
        try (var reader = new BufferedReader(new FileReader(source));
             var writer = new PrintWriter(new FileOutputStream(target))) {
            reader.lines().filter(line -> !line.isBlank()).forEach(this::putInfo);
            writer.write(this.duration.stream()
                    .collect(Collectors.joining(System.lineSeparator())));
        } catch (IOException e) {
            LOG.error("Error reading file: {}", e.getMessage(), e);
            throw new IllegalStateException(e.getMessage());
        }
    }

    /**
     * Put info analise server
     *
     * @param line line to file
     */
    private void putInfo(String line) {
        var s = line.split(" ");
        statusHandler.get(Status.get(s[0])).accept(s[1]);
    }
}
