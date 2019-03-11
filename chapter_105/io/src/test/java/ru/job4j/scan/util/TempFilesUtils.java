package ru.job4j.scan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.scan.SearchTest;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Temp files util
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-03-06
 */
public class TempFilesUtils {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TempFilesUtils.class);

    /**
     * Root file
     *
     */
    public static final File ROOT = new File(System.getProperty("java.io.tmpdir"), "main");

    /**
     * List files
     *
     */
    private List<File> files = new ArrayList<>();

    /**
     * Init
     *
     * @throws IOException if IOException
     */
    public void init() throws IOException {
        try (InputStream stream = SearchTest.class.getClassLoader().getResourceAsStream("filesTree.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)))) {
            files = reader.lines()
                    .map(line -> new File(ROOT, line)).peek(this::create)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Delete files and directories
     *
     */
    public void delete() {
        Stream.of(files, getListDirs())
                .flatMap(Collection::stream)
                .forEach(this::deleteFile);
    }

    /**
     * Create file or directories
     *
     * @param value file or directories
     */
    private void create(File value) {
        File parent = value.getParentFile();
        try {
            if (!parent.exists()) {
                Files.createDirectories(parent.toPath());
            }
            Files.createFile(value.toPath());
        } catch (IOException ex) {
            LOG.error("Error ", ex);
        }
    }

    /**
     * Get list of directories
     *
     * @return list of directories
     */
    private List<File> getListDirs() {
        Queue<File> queue = new LinkedList<>();
        List<File> dirs = new ArrayList<>();
        queue.offer(ROOT);
        while (!queue.isEmpty()) {
            File file = queue.poll();
            if (file.isDirectory()) {
                Arrays.stream(Objects.requireNonNull(file.listFiles()))
                        .filter(File::isDirectory)
                        .forEach(queue::offer);
                dirs.add(0, file);
            }
        }
        return dirs;
    }

    /**
     * Delete file
     *
     * @param file current file
     */
    private void deleteFile(File file) {
        try {
            Files.delete(file.toPath());
        } catch (IOException e) {
            LOG.error("Error deleting file {}", file.getName(), e);
        }
    }
}