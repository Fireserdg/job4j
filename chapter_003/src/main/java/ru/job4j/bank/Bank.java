package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bank operations.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 02.08.2018.
 */
public class Bank {

    /**
     * Contains object HashMap.
     */
    private Map<User, List<Account>> bankAccount = new HashMap<>();

    /**
     * Add User.
     *
     * @param user New user.
     */
    public void addUser(User user) {
        this.bankAccount.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Delete User.
     *
     * @param user User.
     */
    public void deleteUser(User user) {
        this.bankAccount.remove(user);
    }

    /**
     * Add Account to User.
     *
     * @param passport number passport user.
     * @param account account User.
     */
    public void addAccountToUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> user : this.bankAccount.entrySet()) {
            if (user.getKey().getPassport().equals(passport)) {
                user.getValue().add(account);
            }
        }
    }

    /**
     * Delete one account from User.
     *
     * @param passport number passport user.
     * @param account account User.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> user : this.bankAccount.entrySet()) {
            if (user.getKey().getPassport().equals(passport)) {
                user.getValue().remove(account);
            }
        }
    }

    /**
     * Get account for User.
     *
     * @param passport number passport user.
     * @return list of account.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = new ArrayList<>();
        for (Map.Entry<User, List<Account>> user : this.bankAccount.entrySet()) {
            if (user.getKey().getPassport().equals(passport)) {
                accounts.addAll(user.getValue());
            }
        }
        return accounts;
    }

    /**
     * Search User by name.
     *
     * @param name name of User.
     * @return result.
     */
    public boolean searchUser(String name) {
        boolean result = false;
        for (Map.Entry<User, List<Account>> user : this.bankAccount.entrySet()) {
            if (user.getKey().getName().equals(name)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Transfer money between account and other user.
     *
     * @param srcPassport number passport user.
     * @param srcRequisite requisite account user.
     * @param destPassport beneficiary's passport.
     * @param dstRequisite beneficiary's requisite.
     * @param amount amount of money.
     * @return The possibility of transferring the money.
     */

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String dstRequisite, double amount) {
       boolean result = false;
        for (Map.Entry<User, List<Account>> user : this.bankAccount.entrySet()) {
            if (user.getKey().getPassport().equals(srcPassport)) {
                for (Account userAccount: user.getValue()) {
                    if (userAccount.getRequisites().equals(srcRequisite)
                            && userAccount.getValues() > amount) {
                        result = true;
                        break;
                    }
                }
            }
            if (result && user.getKey().getPassport().equals(destPassport)) {
                for (Account userAccount: user.getValue()) {
                    if (!(userAccount.getRequisites().equals(dstRequisite))) {
                        result = false;
                    } else {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
