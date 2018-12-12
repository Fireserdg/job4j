package ru.job4j.crud;

import java.util.List;

/**
 * Message
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 08.11.18
 */

public interface Message {

    /**
     * Key add.
     */
    String ADD = "add";

    /**
     * Key update.
     */
    String UPDATE = "update";

    /**
     * Key delete.
     */
    String DELETE = "delete";

    /**
     * Message about successfully added.
     */
    String MSG_ADD = "User %s successfully added";

    /**
     * Message about exist user.
     */
    String MSG_EXIST = "User with login=%s already exists, please input another login";

    /**
     * Message about does not exist user by id.
     */
    String MSG_ID_NOT_EXIST = "The user with id=%s does not exist, specify the correct id";

    /**
     * Message about successfully update.
     */
    String MSG_UPDATE = "User %s was successfully updated";

    /**
     * Message about successfully deleted.
     */
    String MSG_DELETE = "User with login=%s was successfully delete";

    /**
     * Message about add user.
     */
    List<String> params();
}