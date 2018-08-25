package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

/**
 * UserStorage.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 25.08.2018.
 */
@ThreadSafe
public class UserStorage {

    /**
     * Contains storage.
     *
     */
    @GuardedBy("this")
    private final Map<Integer, User> storage = new HashMap<>();

    /**
     * Add new User in storage.
     *
     * @param user new User.
     * @return true if user add in storage else false.
     */
    public synchronized boolean add(User user) {
        boolean result;
        if (!this.storage.containsKey(user.getId())) {
            this.storage.put(user.getId(), user);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Update User in storage.
     *
     * @param user User.
     * @return true if user update in storage else false.
     */
    public synchronized boolean update(User user) {
        boolean result;
        if (this.storage.containsKey(user.getId())) {
            this.storage.put(user.getId(), user);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Delete User in storage.
     *
     * @param user User.
     * @return true if user delete from storage else false.
     */
    public synchronized boolean delete(User user) {
        boolean result;
        if (this.storage.containsKey(user.getId())) {
            this.storage.remove(user.getId());
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Transfer amount between users.
     *
     * @param fromId user id.
     * @param toId user id.
     * @param amount amount.
     * @return true if operation is successful else false.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result;
        int balanceFrom = this.storage.get(fromId).getAmount();
        int balanceTo = this.storage.get(toId).getAmount();
        if (this.storage.containsKey(fromId) && this.storage.containsKey(toId)
                && balanceFrom >= amount) {
            this.storage.get(fromId).setAmount(balanceFrom - amount);
            this.storage.get(toId).setAmount(balanceTo + amount);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Get storage users.
     *
     * @return storage users.
     */
    public synchronized Map<Integer, User> getStorage() {
        return this.storage;
    }
}