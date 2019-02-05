package ru.job4j.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.*;
import ru.job4j.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Hall Services
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public class HallService implements Service {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HallService.class);

    /**
     * Contains service.
     */
    private static final Service SERVICE = new HallService();

    /**
     * Contains store.
     */
    private final static Store STORE = DbStore.getInstance();

    /**
     * Constructor HallService.
     */
    private HallService() {
    }

    /**
     * Get instance service.
     * @return service.
     */
    public static Service getInstance() {
        return SERVICE;
    }

    /**
     * Add account.
     * @param accounts account.
     * @return message about operation.
     */
    public String addAccount(Accounts accounts) {
        return STORE.addAccount(accounts);
    }

    /**
     * Map halls.
     * @return map halls.
     */
    public Map<Integer, List<Hall>> getHalls() {
        return STORE.getHalls().stream()
                .collect(Collectors.groupingBy(Hall::getRow));
    }

    /**
     * Get hall by id.
     * @param id hall id.
     * @return Hall
     */
    @Override
    public Hall getHallsById(int id) {
        Hall hall;
        try {
            hall =  STORE.getHallsById(id);
        } catch (IllegalArgumentException ex) {
            LOG.error(ex.getMessage(), ex);
            hall = new Hall(-1, 0, 0, 0, true);
        }
        return hall;
    }
}