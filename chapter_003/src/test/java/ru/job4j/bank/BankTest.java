package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test bank operation.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 02.08.2018.
 */
public class BankTest {

    /**
     * Contains object class Bank.
     */
    private Bank bank;

    /**
     * Create object for test methods.
     */
    @Before
    public void createBank() {
        bank = new Bank();
    }

    @Test
    public void whenAddNewUser() {
        User user = new User("Ivan", "444444");
        bank.addUser(user);
        boolean result = bank.searchUser("Ivan");
        assertThat(result, is(true));
    }

    @Test
    public void whenDeleteUser() {
        User userOne = new User("Ivan", "444444");
        User userTwo = new User("Fedor", "444999");
        bank.addUser(userOne);
        bank.addUser(userTwo);
        bank.deleteUser(userOne);
        boolean result = bank.searchUser("Ivan");
        assertThat(result, is(false));
    }

    @Test
    public void whenDeleteAccountFromUser() {
        User user = new User("Fedor", "675432");
        bank.addUser(user);
        Account accountOne = new Account(300, "987023");
        Account accountTwo = new Account(777, "456110");
        bank.addAccountToUser(user.getPassport(), accountOne);
        bank.addAccountToUser(user.getPassport(), accountTwo);
        bank.deleteAccountFromUser(user.getPassport(), accountOne);
        List<Account> result = bank.getUserAccounts(user.getPassport());
        assertThat(result.get(0).getValues(), is(777));
    }

    @Test
    public void whenCheckAccountUser() {
        User user = new User("Alex", "333333");
        bank.addUser(user);
        Account accountOne = new Account(300, "987023");
        bank.addAccountToUser(user.getPassport(), accountOne);
        boolean result = bank.getAccountUser(user.getPassport(), accountOne.getRequisites());
        assertThat(result, is(true));
    }

    @Test
    public void whenUserWantTransferMoneyOtherYourAccountAndSuccessful() {
        User user = new User("Sergey", "123456");
        bank.addUser(user);
        Account accOne = new Account(700, "7654321");
        bank.addAccountToUser(user.getPassport(), accOne);
        Account accTwo = new Account(500, "9876543");
        bank.addAccountToUser(user.getPassport(), accTwo);
        boolean result = bank.transferMoney(user.getPassport(), accOne.getRequisites(),
                user.getPassport(), accTwo.getRequisites(), 500);
        assertThat(result, is(true));
    }

    @Test
    public void whenUserWantTransferMoneyOtherYourAccountAndNotSuccessful() {
        User user = new User("Andrey", "abc1234");
        bank.addUser(user);
        Account accOne = new Account(450, "567432");
        bank.addAccountToUser(user.getPassport(), accOne);
        Account accTwo = new Account(377, "987543");
        bank.addAccountToUser(user.getPassport(), accTwo);
        boolean result = bank.transferMoney(user.getPassport(), accOne.getRequisites(),
                user.getPassport(), accTwo.getRequisites(), 500);
        assertThat(result, is(false));
    }

    @Test
    public void whenUserTransferMoneyOtherUserAndSuccessful() {
        User userOne = new User("Andrey", "abc1234");
        User userTwo = new User("Petr", "abc789");
        bank.addUser(userOne);
        bank.addUser(userTwo);
        Account accOne = new Account(700, "567432");
        Account accTwo = new Account(1500, "987543");
        bank.addAccountToUser(userOne.getPassport(), accOne);
        bank.addAccountToUser(userTwo.getPassport(), accTwo);
        boolean result = bank.transferMoney(userOne.getPassport(), accOne.getRequisites(),
                userTwo.getPassport(), accTwo.getRequisites(), 500);
        assertThat(result, is(true));
    }
}