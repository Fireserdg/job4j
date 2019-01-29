package ru.job4j.persistence;

import ru.job4j.model.Accounts;

import java.util.List;

/**
 * Store for contains info.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public interface Store<T> {

    void addAccount(Accounts accounts);

    void removeAccount(String id);

    List<T> getHalls();

}
