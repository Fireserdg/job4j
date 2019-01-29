package ru.job4j.service;

import ru.job4j.model.Accounts;
import ru.job4j.model.Halls;

import java.util.List;

/**
 * Service
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public interface Service {

    void addAccount(Accounts accounts);

    void removeAccount(String id);

    List<Halls> getHalls();
}
