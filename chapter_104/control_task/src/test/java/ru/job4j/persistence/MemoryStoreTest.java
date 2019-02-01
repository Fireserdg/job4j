package ru.job4j.persistence;

import org.junit.Test;
import ru.job4j.model.Accounts;
import ru.job4j.model.Hall;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Memory store test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 01.02.19
 */
public class MemoryStoreTest {

    @Test
    public void whenAddAccount() {
        MemoryStore store = MemoryStore.getInstance();
        store.addHall(new Hall(11, 1, 1, 500, false));
        store.addHall(new Hall(12, 1, 2, 700, false));
        store.addHall(new Hall(13, 1, 3, 900, false));
        List<Hall> halls = store.getHalls();
        assertThat(halls.size(), is(3));
        String account = store.addAccount(
                new Accounts(11, "Name", "phone"));
        String accountTheSameId = store.addAccount(
                new Accounts(11, "newName", "newPhone"));
        String first = "Билет успешно приобретен";
        String second = "Билет уже купили";
        assertThat(account, is(first));
        assertThat(accountTheSameId, is(second));
        assertThat(store.getHallsById(12).getPrice(), is(700));
        assertThat(store.getHallsById(11).isBooked(), is(true));
    }
}