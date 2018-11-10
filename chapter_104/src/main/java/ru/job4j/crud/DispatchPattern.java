package ru.job4j.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * Contains validate.
     */
    private final ValidateService validate;

    /**
     * Contains destinations.
     */
    private final Map<String, Function<List<String>, String>> dispatch = new HashMap<>();

    /**
     * Contains constructor.
     * @param validate ValidateService.
     */
    public DispatchPattern(final ValidateService validate) {
        this.validate = validate;
    }

    /**
     * Handle add user.
     * @return The message of the add operation.
     */
    private Function<List<String>, String> add() {
        return msg -> this.validate.add(msg.get(1)) == null
                ? String.format(Message.MSG_ADD, msg.get(1))
                : String.format(Message.MSG_EXIST, msg.get(1));
    }

    /**
     * Handle update user.
     * @return The message of the update operation.
     */
    private Function<List<String>, String> update() {
        return msg -> this.validate.update(msg.get(1),
                msg.get(2)) != null ? Message.MSG_UPDATE : String.format(
                        Message.MSG_NOT_EXIST, msg.get(1));
    }

    /**
     * Handle delete user.
     * @return The message of the delete operation.
     */
    private Function<List<String>, String> delete() {

        return msg -> this.validate.delete(msg.get(1)) != null
                ? Message.MSG_DELETE : String.format(Message.MSG_NOT_EXIST, msg.get(1));
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