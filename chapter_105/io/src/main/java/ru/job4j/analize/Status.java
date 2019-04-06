package ru.job4j.analize;

import java.util.Arrays;

/**
 * Status server
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-04-06
 */
public enum Status {

    SUCCESS("200"),

    REDIRECTION("300"),

    CLIENT_ERROR("400"),

    SERVER_ERROR("500");

    /**
     * Status code
     *
     */
    private final String code;

    /**
     * Constructor
     *
     * @param code status code
     */
    Status(String code) {
        this.code = code;
    }

    /**
     * Get status by code
     *
     * @param code status code
     * @return Status
     */
    public static Status get(String code) {
        return Arrays.stream(values())
                .filter(v -> v.code.equals(code))
                .findFirst().orElseThrow();
    }
}
