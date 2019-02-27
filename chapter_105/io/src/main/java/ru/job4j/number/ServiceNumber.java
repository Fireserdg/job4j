package ru.job4j.number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Service for checking even numbers
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-27
 */
public class ServiceNumber {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ServiceNumber.class);

    /**
     * This method checks incoming stream for even numbers
     *
     * @param in stream
     * @return true if stream has eeven number
     */
    public boolean isNumber(InputStream in) {
        var result = false;
        try (var reader = new InputStreamReader(in)) {
            while (reader.ready()) {
                var value = in.read();
                if (value > 48 && value < 58 && (value % 2 == 0)) {
                    result = true;
                    break;
                }
            }
        } catch (IOException e) {
            LOG.error("Error from Input stream", e);
        }
        return result;
    }
}
