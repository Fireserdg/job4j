package ru.job4j.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Config for load properties
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-04-06
 */
public class Config {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Config.class);

    /**
     * Path to file
     *
     */
    private final String path;

    /**
     * Properties map
     *
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Constructor
     *
     * @param path file path
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Load properties
     *
     */
    public void load() {
        try (final var br = new BufferedReader(new FileReader(path))) {
            br.lines().filter(line -> line.contains("=")).forEach(this::putProperties);
        } catch (IOException e) {
            LOG.error("Error reading file: {}", path, e);
            throw new IllegalStateException("File read error");
        }
    }

    /**
     * Get value by key
     *
     * @param key key
     * @return value if exist
     */
    public String value(String key) {
        return Optional.ofNullable(values.get(key))
                .orElseThrow(() -> new IllegalArgumentException("This key doesn't exist"));
    }

    /**
     * Put properties to map
     *
     * @param line line
     */
    private void putProperties(String line) {
        var index = line.indexOf("=");
        values.put(line.substring(0, index), line.substring(index + 1));
    }
}
