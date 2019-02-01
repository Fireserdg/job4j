package ru.job4j.service;

import ru.job4j.model.*;
import java.util.*;

/**
 * Service
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public interface Service {

    /**
     * Message about choose place.
     */
    String MESSAGE = "Вы выбрали место. Нажмите на кнопку ОК";

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
     * Map halls.
     * @return map halls.
     */
    Map<Integer, List<Hall>> getHalls();

    /**
     * Get hall by id.
     * @param id hall id.
     * @return Hall
     */
    Hall getHallsById(int id);
}
