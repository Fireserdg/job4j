package ru.job4j.vacancy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 *  Config from get properties values.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */
public class Config {

    /**
    * Contains properties.
    *
    */
    private final Properties prop = new Properties();

    /**
    * Contains logger.
    *
    */
    private static final Logger LOG = LoggerFactory.getLogger(Config.class);

    /**
    * Load config from property file.
    *
    * @param config name of file.
    */
    public void loadConfig(final String config) {
        ClassLoader cs = Config.class.getClassLoader();
        try (InputStream stream = cs.getResourceAsStream(config);
            InputStreamReader isr = new InputStreamReader(stream, "UTF-8")) {
            prop.load(isr);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
    * Get value by key
    *
    * @param key key.
    * @return value.
    */
    public String getValue(String key) {
        return this.prop.getProperty(key);
    }
}