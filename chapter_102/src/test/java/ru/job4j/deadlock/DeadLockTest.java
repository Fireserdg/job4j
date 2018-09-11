package ru.job4j.deadlock;

import org.junit.Ignore;

/**
 * Test Dead Lock.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 11.09.2018.
 */
public class DeadLockTest {

    @Ignore
    public void whenGetMainDeadlock() throws InterruptedException {
        Thread main = Thread.currentThread();
        main.join();
    }

    @Ignore
    public void whenUseCountDownLatchThenGetDeadLock() throws InterruptedException {
        Thread thread = new Thread(new DeadLock());
        thread.start();
        thread.join();
    }
}