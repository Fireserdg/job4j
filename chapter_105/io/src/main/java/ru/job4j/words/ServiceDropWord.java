package ru.job4j.words;

import java.io.*;
import java.util.Arrays;
import java.util.Set;
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
            var stringSet = Arrays.stream(abuse).collect(Collectors.toSet());
            var words = reader.readLine();
            while (words != null) {
                writer.write(Arrays.stream(words.split("(\\s)"))
                        .filter(value -> !stringSet.contains(
                                value.replaceAll("[^A-Za-zА-Яа-я0-9]+", "")))
                        .collect(Collectors.joining(" ")));
                words = reader.readLine();
                if ((words) != null) {
                    writer.newLine();
                }
            }
        }
    }
}