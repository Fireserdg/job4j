package ru.job4j.pool;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Thread pool.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 04.09.2018.
 */
public class ThreadPool {

    /**
     * Contains list of threads.
     *
     */
    private final List<Thread> threads = new LinkedList<>();

    /**
     * Contains queue of tasks.
     *
     */
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>();

    /**
     * Contains constructor.
     *
     */
    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            this.threads.add(new WorkThread(this.tasks));
        }
    }

    /**
     * Get job.
     *
     * @param job new job.
     */
    public void work(Runnable job) {
        tasks.offer(job);
    }

    /**
     * The performance of the workю
     *
     */
    public void shutDown() {
        for (Thread thread: this.threads) {
            thread.start();
        }
        while (!tasks.isEmpty()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Thread thread: threads) {
            thread.interrupt();
        }
    }

    /**
     * Inner class work Thread.
     *
     */
    private class WorkThread extends Thread {

        /**
         * Contains tasks.
         *
         */
        private final Queue<Runnable> tasks;

        /**
         * Constructor.
         *
         * @param tasks queue of tasks.
         */
        public WorkThread(Queue<Runnable> tasks) {
            this.tasks = tasks;
        }
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Runnable job = tasks.poll();
                if (job != null) {
                    job.run();
                }
            }
        }
    }
}