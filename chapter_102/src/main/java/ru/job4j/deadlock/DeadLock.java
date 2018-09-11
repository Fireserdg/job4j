package ru.job4j.deadlock;

import java.util.concurrent.CountDownLatch;

/**
 * Dead Lock.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 11.09.2018.
 */
public class DeadLock implements Runnable {

    /**
     * Contains count.
     *
     */
    private final CountDownLatch count;

    /**
     * Constructor.
     *
     */
    public DeadLock() {
        this.count = new CountDownLatch(2);
    }

    @Override
    public void run() {
        Thread one = new Thread(() -> {
            try {
                count.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.countDown();
        });
        Thread two = new Thread(() -> {
            count.countDown();
            try {
                count.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        one.start();
        two.start();
        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}