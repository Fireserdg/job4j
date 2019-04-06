package ru.job4j.analize.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 *  Analise util
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-04-06
 */
public final class AnaliseUtil {

    public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    public static final String SOURCE = "server.logs";

    public static final String TARGET = "unavailable.csv";

    public static final List<String> EXPECTED_LIST = List.of(
            "10:57:01;10:59:01;", "11:01:01;11:02:01;", "11:04:01;11:06:01;");

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AnaliseUtil.class);

    /**
     * Search file
     *
     * @param file file
     * @return true if file exists
     */
    public static boolean searchFile(File file) {
        return file.getName().equals(SOURCE) || file.getName().equals(TARGET);
    }

    /**
     * Delete file
     *
     * @param file file
     */
    public static void deleteFile(File file) {
        try {
            Files.delete(file.toPath());
            LOG.info("File [{}] deleted", file.getName());
        } catch (IOException e) {
            LOG.error("Error deleting file {}", file.getName(), e);
        }
    }
}
