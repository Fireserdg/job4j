package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        User result = bank.searchUser("444444");
        assertThat(result.getName(), is("Ivan"));
    }

    @Test (expected = UserDoesNotExistException.class)
    public void whenDeleteUser() {
        User userOne = new User("Ivan", "444444");
        User userTwo = new User("Fedor", "444999");
        bank.addUser(userOne);
        bank.addUser(userTwo);
        bank.deleteUser(userOne);
        bank.searchUser("Ivan");
    }

    @Test
    public void whenDeleteAccountFromUser() {
        User user = new User("Fedor", "675432");
        bank.addUser(user);
        Account accountOne = new Account(300.0, "987023");
        Account accountTwo = new Account(777.0, "456110");
        bank.addAccountToUser(user.getPassport(), accountOne);
        bank.addAccountToUser(user.getPassport(), accountTwo);
        bank.deleteAccountFromUser(user.getPassport(), accountOne);
        List<Account> result = bank.getUserAccounts(user.getPassport());
        assertThat(result.get(0).getValues(), is(777.0));
    }

    @Test
    public void whenUserWantTransferMoneyOtherYourAccountAndSuccessful() {
        User user = new User("Sergey", "123456");
        bank.addUser(user);
        Account accOne = new Account(700.0, "7654321");
        bank.addAccountToUser(user.getPassport(), accOne);
        Account accTwo = new Account(500.0, "9876543");
        bank.addAccountToUser(user.getPassport(), accTwo);
        boolean result = bank.transferMoney(user.getPassport(), accOne.getRequisites(),
                user.getPassport(), accTwo.getRequisites(), 500);
        assertThat(result, is(true));
    }

    @Test
    public void whenUserWantTransferMoneyOtherYourAccountAndNotSuccessful() {
        User user = new User("Andrey", "abc1234");
        bank.addUser(user);
        Account accOne = new Account(450.0, "567432");
        bank.addAccountToUser(user.getPassport(), accOne);
        Account accTwo = new Account(377.0, "987543");
        bank.addAccountToUser(user.getPassport(), accTwo);
        boolean result = bank.transferMoney(user.getPassport(), accOne.getRequisites(),
                user.getPassport(), accTwo.getRequisites(), 500.0);
        assertThat(result, is(false));
    }

    @Test
    public void whenUserTransferMoneyOtherUserAndSuccessful() {
        User userOne = new User("Andrey", "abc1234");
        User userTwo = new User("Petr", "abc789");
        bank.addUser(userOne);
        bank.addUser(userTwo);
        Account accOne = new Account(700.0, "567432");
        Account accTwo = new Account(1500.0, "987543");
        bank.addAccountToUser(userOne.getPassport(), accOne);
        bank.addAccountToUser(userTwo.getPassport(), accTwo);
        boolean result = bank.transferMoney(userOne.getPassport(), accOne.getRequisites(),
                userTwo.getPassport(), accTwo.getRequisites(), 500.0);
        assertThat(result, is(true));
    }

    @Test
    public void whenUserTransferMoneyOtherUserAndCheckValues() {
        User userOne = new User("Andrey", "abc1234");
        User userTwo = new User("Petr", "abc789");
        bank.addUser(userOne);
        bank.addUser(userTwo);
        Account accOne = new Account(1000.0, "567432");
        Account accTwo = new Account(1500.0, "987543");
        bank.addAccountToUser(userOne.getPassport(), accOne);
        bank.addAccountToUser(userTwo.getPassport(), accTwo);
        boolean result = bank.transferMoney(userOne.getPassport(), accOne.getRequisites(),
                userTwo.getPassport(), accTwo.getRequisites(), 499.0);
        assertThat(accTwo.getValues(), is(1999.0));
        assertThat(result, is(true));
    }

    @Test (expected = UserHasNotBankAccountException.class)
    public void whenDeleteAccountToUserThenGetException() {
        User userOne = new User("Andrey", "abc1234");
        Account accOne = new Account(1000.0, "567432");
        Account result = new Account(100.0, "789990");
        bank.addUser(userOne);
        bank.addAccountToUser(userOne.getPassport(), accOne);
        bank.deleteAccountFromUser("abc1234", result);
    }

    @Test (expected = UserHasNotBankAccountException.class)
    public void whenGetListOfAccountButUserHasNotAccountThenException() {
        User userOne = new User("Andrey", "abc1234");
        bank.getUserAccounts(userOne.getPassport());
    }

    @Test
    public void whenUserWantTransferMoneyOtherYourAccountAndThenGetMessage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        User userOne = new User("Andrey", "abc1234");
        User userTwo = new User("Petr", "abc789");
        bank.addUser(userOne);
        bank.addUser(userTwo);
        bank.addAccountToUser(userOne.getPassport(), new Account(700.0, "567432"));
        bank.addAccountToUser(userTwo.getPassport(), new Account(1500.0, "987543"));
        boolean result = bank.transferMoney(userOne.getPassport(), "567433",
                userTwo.getPassport(), "987543", 500.0);
        assertThat(result, is(false));
        assertThat(out.toString(), is("This account does not exist"));
        System.setOut(System.out);
    }

    @Test
    public void checkEqualsAndHashCodeForClasses() {
        User userOne = new User("Andrey", "abc1234");
        User userTwo = new User("Andrey", "abc1234");
        User userThree = new User("Andrey", "abc1235");
        Account accOne = new Account(1000.0, "567432");
        Account accTwo = new Account(1001.0, "567432");
        Account accThree = new Account(0.0, "567433");
        boolean result = userOne.equals(userTwo);
        assertThat(result, is(true));
        result = accOne.equals(accTwo);
        assertThat(result, is(true));
        result = userOne.equals(userThree);
        assertThat(result, is(false));
        result = accOne.equals(accThree);
        assertThat(result, is(false));
        assertThat(userOne.hashCode(), is(userTwo.hashCode()));
        assertNotSame(userOne.hashCode(), userThree.hashCode());
        assertThat(accOne.hashCode(), is(accTwo.hashCode()));
        assertNotSame(accOne.hashCode(), accThree.hashCode());
    }
}