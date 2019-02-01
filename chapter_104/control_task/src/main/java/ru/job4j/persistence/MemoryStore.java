package ru.job4j.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.*;
import ru.job4j.service.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Memory store
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public class MemoryStore implements Store {
    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MemoryStore.class);

    /**
     * Contains halls halls.
     */
    private final Map<Integer, Hall> halls = new ConcurrentHashMap<>();

    /**
     * Contains halls accounts.
     */
    private final Map<Integer, Accounts> list = new ConcurrentHashMap<>();

    /**
     * Instance Memory store.
     */
    private static final MemoryStore STORE = new MemoryStore();

    /**
     * Constructor MemoryStore.
     */
    private MemoryStore() {
    }

    /**
     * Get instance store.
     * @return instance store.
     */
    public static MemoryStore getInstance() {
        return STORE;
    }

    /**
     * Add account.
     * @param accounts account.
     * @return message about operation.
     */
    @Override
    public String addAccount(Accounts accounts) {
        String msg;
        if (list.get(accounts.getId()) != null) {
            msg = Service.NOT_HALL;
            LOG.info(Service.ADD_ACCOUNT, accounts);
        } else {
            list.put(accounts.getId(), accounts);
            halls.get(accounts.getId()).setBooked(true);
            msg = Service.BUY_HALL;
        }
        return msg;
    }

    /**
     * List of halls.
     * @return halls of halls.
     */
    @Override
    public List<Hall> getHalls() {
        return new ArrayList<>(this.halls.values());
    }

    /**
     * Get hall by id.
     * @param id hall id.
     * @return Hall
     */
    @Override
    public Hall getHallsById(int id) {
        return halls.get(id);
    }

    /**
     * Add hall.
     *
     * @param hall hall.
     */
    public void addHall(Hall hall) {
        this.halls.putIfAbsent(hall.getId(), hall);
        LOG.info("Add hall: {}", hall);
    }
}
