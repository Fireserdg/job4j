package ru.job4j.storage;

import org.junit.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test UserStorage.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 25.08.2018.
 */
public class UserStorageTest {

    /**
     * Contains storage for Users.
     *
     */
    private UserStorage storage;

    /**
     * Contains Thread.
     *
     */
    private Thread first;

    /**
     * Create new Thread and new UserStorage for test.
     *
     */
    @Before
    public void createThread() {
        storage = new UserStorage();
        first = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.add(new User(1, 1500));
                storage.add(new User(2, 370));
                storage.add(new User(3, 750));

            }
        });
    }

    @Test
    public void whenAdd5UsersInStorage() throws InterruptedException {
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.add(new User(4, 1000));
                storage.add(new User(5, 800));
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(storage.getStorage().get(1).getAmount(), is(1500));
        assertThat(storage.getStorage().get(2).getAmount(), is(370));
        assertThat(storage.getStorage().get(3).getAmount(), is(750));
        assertThat(storage.getStorage().get(4).getAmount(), is(1000));
        assertThat(storage.getStorage().get(5).getAmount(), is(800));
    }

    @Test
    public void whenAdd4UserThenDeleteOneAndUpdateOne() throws InterruptedException {
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storage.delete(new User(1, 1500));
                storage.update(new User(2, 3000));
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertNull(storage.getStorage().get(1));
        assertThat(storage.getStorage().get(2).getAmount(), is(3000));
    }

    @Test
    public void whenOneUserTransferMoneyOtherUser() throws InterruptedException {
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storage.transfer(1, 2, 1000);
                storage.transfer(2, 3, 500);
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(storage.getStorage().get(1).getAmount(), is(500));
        assertThat(storage.getStorage().get(2).getAmount(), is(870));
        assertThat(storage.getStorage().get(3).getAmount(), is(1250));
    }

    @Test
    public void whenAddUsersThenDeleteAndUpdateAndTransferAmount() {
        UserStorage users = new UserStorage();
        assertThat(users.add(new User(1, 500)), is(true));
        assertThat(users.add(new User(2, 1000)), is(true));
        assertThat(users.add(new User(3, 800)), is(true));
        assertThat(users.add(new User(4, 1500)), is(true));
        assertThat(users.add(new User(1, 500)), is(false));
        assertThat(users.delete(new User(4, 1500)), is(true));
        assertThat(users.update(new User(2, 1500)), is(true));
        boolean result = users.transfer(1, 3, 500);
        assertThat(result, is(true));
        result = users.transfer(2, 1, 1501);
        assertThat(result, is(false));
    }
}