package ru.job4j.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.*;

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
    private final Map<Integer, Accounts> userAccounts = new ConcurrentHashMap<>();

    /**
     * Instance Memory store.
     */
    private static final MemoryStore STORE = new MemoryStore();

    /**
     * Constructor MemoryStore.
     */
    private MemoryStore() {
        initHalls();
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
     * @param account account.
     * @return message about operation.
     */
    @Override
    public String addAccount(Accounts account) {
        Accounts value = userAccounts.computeIfAbsent(
                account.getId(), key -> {
            halls.get(account.getId()).setBooked(true);
            return account;
        });
        return getMessage(value, account);
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
     */
    public void initHalls() {
        halls.put(11, new Hall(11, 1, 1, 500, false));
        halls.put(12, new Hall(12, 1, 2, 700, false));
        halls.put(13, new Hall(13, 1, 3, 900, false));
        halls.put(21, new Hall(21, 2, 1, 900, false));
        halls.put(22, new Hall(22, 2, 2, 900, false));
        halls.put(23, new Hall(23, 2, 3, 900, false));
        halls.put(31, new Hall(31, 3, 1, 900, false));
        halls.put(32, new Hall(32, 3, 2, 900, false));
        halls.put(33, new Hall(33, 3, 3, 900, false));
    }

    /**
     * Get list of accounts.
     *
     * @return list accounts.
     */
    public List<Accounts> getAccounts() {
        return new ArrayList<>(this.userAccounts.values());
    }

    /**
     * Get message about operation by add account.
     * @param resultPut result of adding an account to accounts.
     * @param newAcc account we want to add.
     * @return message.
     */
    private String getMessage(Accounts resultPut, Accounts newAcc) {
        String msg;
        if (Objects.equals(resultPut, newAcc)) {
            msg = BUY_HALL;
            LOG.info("Added a new account {}", newAcc);
        } else {
            msg = NOT_HALL;
        }
        return msg;
    }
}
