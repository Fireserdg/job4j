package ru.job4j.switcher;

import java.util.concurrent.Semaphore;

/**
 * Switcher.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $1.0$
 * @since 12.09.2018.
 */
public class Switcher implements Runnable {

    /**
     * Contains string.
     *
     */
    private StringBuilder string = new StringBuilder();

    /**
     * Contains one Semaphore.
     *
     */
    private final Semaphore semOne;

    /**
     * Contains two Semaphore.
     *
     */
    private final Semaphore semTwo;

    /**
     * Contains count for cycle.
     *
     */
    private final int count;

    /**
     * Constructor.
     *
     * @param value value for cycle.
     */
    public Switcher(final int value) {
        this.semOne = new Semaphore(0);
        this.semTwo = new Semaphore(1);
        this.count = value;
    }

    /**
     * Add value in string.
     *
     * @param value value.
     */
    public void add(int value) {
        this.string.append(value);
    }

    /**
     * Get string.
     *
     * @return string.
     */
    public String getString() {
        return this.string.toString();
    }

    @Override
    public void run() {
        Thread one = new Thread(() -> {
            for (int i = 0; i < this.count; i++) {
                try {
                    this.semTwo.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 10; j++) {
                    this.add(1);
                }
                this.semOne.release();
            }

        });
        Thread two = new Thread(() -> {
            for (int i = 0; i < this.count; i++) {
                try {
                    this.semOne.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 10; j++) {
                    this.add(2);
                }
                this.semTwo.release();
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