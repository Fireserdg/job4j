package ru.job4j.words;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Service for drop word
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-03-02
 */
public class ServiceDropWord {

    /**
     * Drop abuses from stream
     *
     * @param in Input stream
     * @param out OutputStream
     * @param abuse abuse
     * @throws IOException if problem with stream
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(in));
             var writer = new BufferedWriter(new OutputStreamWriter(out))) {
            var regEx = getRegExp(abuse);
            writer.write(reader.lines()
                    .map(line -> line.replaceAll(regEx, ""))
                    .collect(Collectors.joining(System.lineSeparator())));
        }
    }

    private String getRegExp(String[] abuse) {
        return Arrays.stream(abuse)
                .collect(Collectors.collectingAndThen(Collectors.joining("|", "(", ")"),
                        rst -> String.format("%s%s", "(?i)", rst)));
    }
}