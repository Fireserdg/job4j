package ru.job4j.problem;

/**
 * Problem in Multithreading.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 24.08.2018.
 */
public class TestProblem {

    /**
     * Contains count;
     *
     */
    private int count;

    /**
     * Main method.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        TestProblem testProblem = new TestProblem();
        testProblem.increment();
    }

    /**
     * Increment count using two threads.
     *
     */
    public void increment() {

        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    count++;
                }
            }
        });

        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    count++;
                }
            }
        });

        first.start();
        second.start();
        System.out.println(count);
    }
}