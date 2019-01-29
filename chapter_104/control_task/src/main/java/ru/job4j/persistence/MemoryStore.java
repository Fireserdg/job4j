package ru.job4j.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.Accounts;

import java.util.List;

/**
 * Memory store
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public class MemoryStore implements Store<Accounts> {
    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MemoryStore.class);

    private static final MemoryStore STORE = new MemoryStore();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return STORE;
    }

    @Override
    public void addAccount(Accounts accounts) {

    }

    @Override
    public void removeAccount(String id) {

    }

    @Override
    public List<Accounts> getHalls() {
        return null;
    }
}
