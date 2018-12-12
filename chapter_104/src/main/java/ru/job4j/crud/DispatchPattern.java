package ru.job4j.crud;

import java.util.*;
import java.util.function.Function;

/**
 * Dispatch Pattern
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 07.11.18
 */
public class DispatchPattern {

    /**
     * Contains class instance.
     */
    private static final DispatchPattern INSTANCE = new DispatchPattern();

    /**
     * Contains validate.
     */
    private static final Validate VALIDATE = ValidateService.getInstance();

    /**
     * Contains destinations.
     */
    private final Map<String, Function<List<String>, String>> dispatch = new HashMap<>();

    /**
     * Contains constructor.
     */
    private DispatchPattern() {
    }

    /**
     * Get instance.
     * @return instance of DispatchPattern.
     */
    public static DispatchPattern getInstance() {
        return INSTANCE;
    }

    /**
     * Handle add user.
     * @return The message of the add operation.
     */
    private Function<List<String>, String> add() {
        return msg -> VALIDATE.add(msg.stream().filter(n -> !n.equals("add"))
                .toArray(String[]::new));
    }

    /**
     * Handle update user.
     * @return The message of the update operation.
     */
    private Function<List<String>, String> update() {
        return msg -> VALIDATE.update(msg.stream().
                filter(n -> !n.equals("update")).toArray(String[]::new));
    }

    /**
     * Handle delete user.
     * @return The message of the delete operation.
     */
    private Function<List<String>, String> delete() {
        return msg -> VALIDATE.delete(msg.get(1));
    }

    /**
     * Init's dispatch.
     * @return current object.
     */
    public DispatchPattern init() {
        this.load(Message.ADD, this.add());
        this.load(Message.UPDATE, this.update());
        this.load(Message.DELETE, this.delete());
        return this;
    }

    /**
     * Load message about operation for destinations.
     * @param type Message type.
     * @param handle Handler.
     */
    public void load(String type, Function<List<String>, String> handle) {
        this.dispatch.put(type, handle);
    }

    /**
     * Sent message to dispatch.
     * @param msg message
     * @return msg about operation.
     */
    public String sent(final Message msg) {
        return this.dispatch.get(msg.params().get(0)).apply(msg.params());
    }
}