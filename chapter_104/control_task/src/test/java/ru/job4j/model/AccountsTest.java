package ru.job4j.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Account test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 01.02.19
 */
public class AccountsTest {

    @Test
    public void whenCreateAccountThenGetParam() {
        Accounts accounts = new Accounts(23,
                "Petrov Alex Nikolaevich", "+7(111)2223344");
        assertThat(accounts.getId(), is(23));
        assertThat(accounts.getUsername(), is("Petrov Alex Nikolaevich"));
        assertThat(accounts.getPhone(), is("+7(111)2223344"));

    }

    @Test
    public void whenComparingTwoObjectsHall() {
        Accounts accounts = new Accounts(23,
                "Petrov Alex Nikolaevich", "+7(111)2223344");
        Accounts theSame = new Accounts(23,
                "Petrov Alex Nikolaevich", "+7(111)2223344");
        Accounts other = new Accounts(23,
                "Ivanov Ivan Nikolaevich", "+7(222)2223344");
        assertThat(accounts.equals(theSame), is(true));
        assertThat(accounts.equals(other), is(false));
        assertThat(accounts.hashCode() == theSame.hashCode(), is(true));
        assertThat(accounts.hashCode() == other.hashCode(), is(false));
    }

    @Test
    public void whenGetToStringHall() {
        Accounts accounts = new Accounts(23,
                "Petrov Alex Nikolaevich", "+7(111)2223344");
        String result = String.format(
                "Accounts{id=%s, username=%s, phone=%s}",
                23, "Petrov Alex Nikolaevich", "+7(111)2223344");
        assertThat(accounts.toString(), is(result));
    }
}