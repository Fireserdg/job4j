package ru.job4j.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.config.Config;
import ru.job4j.model.Accounts;
import ru.job4j.model.Halls;

import java.util.List;

/**
 * DataBase store
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public class DbStore implements Store<Halls> {
    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DbStore.class);

    private static final Config CONFIG = Config.getInstance();

    private static final Store STORE = new DbStore();

    private DbStore() {
        init();
    }

    private void init() {

    }

    public static Store getInstance() {
        return STORE;
    }

    public void addAccount(Accounts accounts) {

    }

    public void removeAccount(String id) {

    }

    public List<Halls> getHalls() {
        return null;
    }


}
