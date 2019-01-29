package ru.job4j.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.Accounts;
import ru.job4j.model.Halls;
import ru.job4j.persistence.DbStore;
import ru.job4j.persistence.Store;

import java.util.List;

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

    private static final Service SERVICE = new HallService();

    private final Store store = DbStore.getInstance();

    private HallService() {
    }

    public static Service getInstance() {
        return SERVICE;
    }

    public void addAccount(Accounts accounts) {

    }

    public void removeAccount(String id) {

    }

    public List<Halls> getHalls() {
        return null;
    }


}
