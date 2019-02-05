package ru.job4j.persistence;

import ru.job4j.model.Accounts;
import ru.job4j.model.Hall;

import java.util.List;

/**
 * Store for contains info.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public interface Store {

    /**
     * Message about add account.
     */
    String ADD_ACCOUNT = "Билет приобретен. Добавлен аккаунт {}";

    /**
     * Message about buy hall.
     */
    String BUY_HALL = "Билет успешно приобретен";

    /**
     * Message about that place booked.
     */
    String NOT_HALL = "Билет уже купили";

    /**
     * Add account.
     * @param accounts account.
     * @return message about operation.
     */
    String addAccount(Accounts accounts);

    /**
     * List of halls.
     * @return list of halls.
     */
    List<Hall> getHalls();

    /**
     * Get hall by id.
     * @param id hall id.
     * @return Hall
     */
    Hall getHallsById(int id);
}
