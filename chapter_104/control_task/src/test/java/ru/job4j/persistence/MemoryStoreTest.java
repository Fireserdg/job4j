package ru.job4j.persistence;

import org.junit.Test;
import ru.job4j.model.Accounts;
import java.util.stream.IntStream;

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
    public void whenAddAccount() throws InterruptedException {
        MemoryStore store = MemoryStore.getInstance();
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
        IntStream.range(0, 10).forEach(
                value -> new Thread(() -> store.addAccount(
                        new Accounts(12, String.format("name:%s", value),
                                "phone"))).start());
        IntStream.range(0, 10).forEach(
                value -> new Thread(() -> store.addAccount(
                        new Accounts(23, String.format("name:%s", value),
                                "phone"))).start());
        Thread.sleep(300);
        assertThat(store.getHallsById(12).isBooked(), is(true));
        assertThat(store.getHallsById(23).isBooked(), is(true));
        assertThat(store.getAccounts().size(), is(3));
    }
}