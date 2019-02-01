package ru.job4j.service;

import ru.job4j.model.*;
import ru.job4j.persistence.*;

import java.util.*;

/**
 * Hall Services
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public class HallService implements Service {

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
        Map<Integer, List<Hall>> map = new HashMap<>();
        for (Hall hall : STORE.getHalls()) {
            if (map.get(hall.getRow()) != null) {
                map.computeIfPresent(hall.getRow(), (k, v) -> {
                    v.add(hall);
                    return v;
                });
            } else {
                List<Hall> list = new ArrayList<>();
                list.add(hall);
                map.put(hall.getRow(), list);
            }
        }
        return map;
    }

    /**
     * Get hall by id.
     * @param id hall id.
     * @return Hall
     */
    @Override
    public Hall getHallsById(int id) {
        return STORE.getHallsById(id);
    }


}
