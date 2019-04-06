package ru.job4j.analize;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.analize.util.AnaliseUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.analize.util.AnaliseUtil.*;

/**
 * Analise test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-04-06
 */
public class AnaliseTest {

    @Before
    public void setUp() throws IOException {
        Files.copy(Path.of(SOURCE), Path.of(String.format("%s%s%s", TEMP_DIR, File.separator, SOURCE)));
    }

    @After
    public void clear() {
        Arrays.stream(Objects.requireNonNull(new File(TEMP_DIR).listFiles()))
                .filter(AnaliseUtil::searchFile).forEach(AnaliseUtil::deleteFile);
    }

    @Test
    public void whenGetInfoAboutServerError() throws IOException {
        var target = new File(TEMP_DIR, TARGET);
        new Analise().unavailable(SOURCE, target.getPath());
        try (var reader = new BufferedReader(new FileReader(target))) {
            List<String> result = reader.lines().collect(Collectors.toList());
            assertThat(result, is(EXPECTED_LIST));
        }
    }

    @Test(expected = IllegalStateException.class)
    public void whenGetInfoAboutServerErrorThenException() {
        new Analise().unavailable("handler.log", TARGET);
    }
}