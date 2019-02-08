package ru.job4j.bank;

import java.util.*;

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
    private final Map<User, List<Account>> bankAccount = new HashMap<>();

    private final String msgUser = "User does not exist, please enter a valid passport number";

    private final String msgAcc = "This account does not exist";

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
        this.bankAccount.remove(searchUser(user.getPassport()));
    }

    /**
     * Add Account to User.
     *
     * @param passport number passport user.
     * @param account account User.
     */
    public void addAccountToUser(String passport, Account account) {
        this.bankAccount.get(searchUser(passport)).add(account);
    }

    /**
     * Delete one account from User.
     *
     * @param passport number passport user.
     * @param account account User.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        if (!(getUserAccounts(passport).remove(account))) {
            throw new UserHasNotBankAccountException(this.msgAcc);
        }
    }

    /**
     * Get list accounts for User.
     *
     * @param passport number passport user.
     * @return list of account.
     */
    public List<Account> getUserAccounts(String passport) {
        Optional<List<Account>> value = this.bankAccount.entrySet().stream()
                .filter(entry -> entry.getKey()
                .equals(searchUser(passport)))
                .map(Map.Entry::getValue).findFirst();
        if (!value.isPresent()) {
            throw new UserHasNotBankAccountException(this.msgAcc);
        }
        return value.get();
    }

    /**
     * Get one account for User.
     *
     * @param passport number passport user.
     * @param requisite number requisite user.
     * @return result if account has user.
     */
    public Account getAccountUser(String passport, String requisite) {
        return getUserAccounts(passport).stream()
                .filter(account -> account.getRequisites()
                .equals(requisite))
                .findFirst().orElseThrow(() -> new UserHasNotBankAccountException(this.msgAcc));
    }

    /**
     * Search User by name.
     *
     * @param passport passport of User.
     */
    public User searchUser(String passport) {
        return this.bankAccount.keySet().stream()
                .filter(user -> user.getPassport()
                .equals(passport))
                .findFirst()
                .orElseThrow(() -> new UserDoesNotExistException(this.msgUser));
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
        try {
            Account one = getAccountUser(srcPassport, srcRequisite);
            Account two = getAccountUser(destPassport, dstRequisite);
            if (one.getValues() > amount) {
                one.setValue(one.getValues() - amount);
                two.setValue(two.getValues() + amount);
                result = true;
            }
        } catch (UserHasNotBankAccountException | UserDoesNotExistException ex) {
            System.out.print(ex.getMessage());
        }
        return result;
    }
}
