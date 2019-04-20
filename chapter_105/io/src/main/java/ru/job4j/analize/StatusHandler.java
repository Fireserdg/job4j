package ru.job4j.analize;

import java.util.*;
import java.util.function.Consumer;

/**
 * Status Handler
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-04-06
 */
public class StatusHandler {

    private final Map<Status, Consumer<String>> dispatch = new HashMap<>();

    private final StringBuilder sb = new StringBuilder();

    private List<String> info;

    public boolean flag;

    public StatusHandler load(List<String> info) {
        this.info = info;
        dispatch.put(Status.SUCCESS, this.successResponse());
        dispatch.put(Status.REDIRECTION, this.successResponse());
        dispatch.put(Status.CLIENT_ERROR, this.failedResponse());
        dispatch.put(Status.SERVER_ERROR, this.failedResponse());
        return this;
    }

    public Consumer<String> get(Status status) {
        return dispatch.get(status);
    }

    private Consumer<String> failedResponse() {
        return x -> {
            if (!flag) {
                flag = true;
                sb.append(x);
            }
        };
    }

    private Consumer<String> successResponse() {
        return x -> {
            if (flag) {
                info.add(String.format("%s;%s;", sb.toString(), x));
                flag = false;
                sb.setLength(0);
            }
        };
    }
}